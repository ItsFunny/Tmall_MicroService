/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午9:54:00
* 
*/
package com.tmall.system.admin.controller;

import static org.assertj.core.api.Assertions.useRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jca.cci.RecordTypeNotSupportedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.service.IdWorkerService;
import com.joker.library.utils.JsonUtil;
import com.tmall.common.constants.UserRequestConstant;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.enums.StoreStatusEnum;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.internal.spi.service.IInternalCategoryServiece;
import com.tmall.internal.spi.service.IInternalFacadedService;
import com.tmall.internal.spi.service.IInternalStoreService;
import com.tmall.server.spi.gateway.brand.IGatewayBrandService;
import com.tmall.server.spi.gateway.category.IGatewayCategoryService;
import com.tmall.server.spi.gateway.property.IGatewayProductServerPropertyFeignService;
import com.tmall.server.spi.gateway.store.IGatewayStoreFeignService;
import com.tmall.server.spi.store.IStoreServerFeignService;
import com.tmall.system.admin.model.BrandFormModel;
import com.tmall.system.admin.model.CategoryFormModel;
import com.tmall.system.admin.service.IBrandService;
import com.tmall.system.admin.util.AdminUtil;

/**
 * 迫于非前后端分离,采取本地调用服务接口然后再开放接口的方式 method name shoud be followed by thie
 * {server-name}/{method-detail} and method-detail should be clear and simple
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 上午9:54:00
 */
@RestController
@RequestMapping("/admin/api/v1")
public class RestAPIController
{
	// @Autowired
	// private IProductServerCategoryFeignService categoryFeignSerivce;

	// @Autowired
	// private IFacadedService facadedService;
	@Autowired
	private IGatewayStoreFeignService gatewayStoreFeignService;
	@Autowired
	private IGatewayProductServerPropertyFeignService propertyFeignService;
	@Autowired
	private IGatewayBrandService gatewayBrandService;
	@Autowired
	private IdWorkerService idWorkerService;

	@Autowired
	private IStoreServerFeignService storeServerFeignService;

	@Autowired
	private IGatewayCategoryService categoryService;

	@Autowired
	private IBrandService brandService;
	// 内部接口
	@Autowired
	private IInternalCategoryServiece internalCategoryService;
	@Autowired
	private IInternalStoreService innternalStoreService;
	
	@Autowired
	private IInternalFacadedService internalFacadedService;

	// 显示商家下的所有类目
	// @RequestMapping(value = "/category/topLevel/all", method =
	// { RequestMethod.POST, RequestMethod.GET }, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public ResultDTO<List<CategoryDTO>> findCategoriesAllTopLevel()
	// {
	// try
	// {
	// Long storeId = AdminUtil.getLoginUser().getSelfRole().getStoreId();
	// ResultDTO<List<CategoryDTO>> resultDTO =
	// categoryFeignSerivce.findStoreAllTopLevelCategories(storeId);
	// return resultDTO;
	// } catch (Exception e)
	// {
	// return ResultUtils.fail(e.getMessage());
	// }
	// }
	// @RequestMapping(value = "/category/child/{parentCategoryId}", method =
	// { RequestMethod.POST, RequestMethod.GET }, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public ResultDTO<List<CategoryDTO>> findALlChilds(@PathVariable Integer
	// parentCategoryId)
	// {
	// try
	// {
	// return categoryFeignSerivce.findCateogryChilds(parentCategoryId);
	// } catch (Exception e)
	// {
	// return ResultUtils.fail();
	// }
	// }
	// @RequiresPermissions("edit_store_show_detail")
	// @RequestMapping(value="/store/detail/{storeId}")
	// public ResultDTO<StoreDTO>showStoreDetail(@PathVariable("storeId")Long
	// storeId)
	// {
	// //调用store服务的接口 ,,这里记得改为通过zuul调用
	// return facadedService.findStoreDetail(storeId);
	// }


	/*
	 * 显示店铺详情
	 */
	@RequiresPermissions(value =
	{ "edit_store_show_detail" })
	@RequestMapping(value = "/store/detail/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<StoreDetail> showStoreDetail(@PathVariable("storeId") Long storeId, HttpServletRequest request)
	{
		ResultDTO<StoreDetail> resultDTO = storeServerFeignService.findStoreDetail(storeId);
		return resultDTO;
	}

	@RequestMapping(value = "/store/updateStatus/{storeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> updateStoreStatus(@PathVariable("storeId") Long storeId,
			@RequestParam("status") Integer status)
	{
		// 这里好像只能通过代码控制权限了啊,,除非将这些接口一个一个对应的拆开
		if (status.equals(StoreStatusEnum.PASS.ordinal()))
		{
			AdminUtil.checkStringPermission("edit_store_approve");
		} else if (status.equals(StoreStatusEnum.VERIFY_FAIL.ordinal()))
		{
			AdminUtil.checkStringPermission("edit_store_refuse");
		} else if (status.equals(StoreStatusEnum.CLOSE.ordinal()))
		{
			AdminUtil.checkStringPermission("edit_store_close");
		}
		// 修改状态,通过zuul调用接口
		return gatewayStoreFeignService.updateStoreStatusByStoreId(storeId, status);
	}

	/*
	 * 添加品牌
	 */
	@RequestMapping(value = "/auth/brand/add")
	public ResultDTO<String> addBrand(@Valid BrandFormModel formModel, BindingResult result, HttpServletRequest request,
			HttpServletResponse response)
	{
		String error = "";
		if (result.hasErrors())
		{
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors)
			{
				error += objectError.getDefaultMessage();
			}
		}
		if (!StringUtils.isEmpty(error))
		{
			return ResultUtils.fail(error);
		}
		// BrandDTO brandDTO = new BrandDTO();
		// formModel.to(brandDTO);
		// formModel = null;
		// ResultDTO<String> resultDTO = gatewayBrandService.addBrand(brandDTO);
		// if(resultDTO.isSuccess())
		// {
		// }
		ResultDTO<String> resultDTO = brandService.addOrUpdateBrand(formModel);
		return resultDTO;
	}

	@GetMapping(value = "/auth/brand/delete")
	public ResultDTO<String> deleteBrand(@RequestParam("ids") String ids)
	{
		String[] idArray = ids.split(",");
		if (null == idArray)
		{
			return com.joker.library.utils.ResultUtils.fail("要删除的品牌id不能为空");
		}
		UserDTO user = AdminUtil.getLoginUser();
		final List<Integer> idList = new ArrayList<Integer>();
		Stream.of(idArray).forEach(id -> {
			idList.add(Integer.parseInt(id));
		});
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUser(user);
		Map<String, Object> extProps = new HashMap<>();
		extProps.put(UserRequestConstant.USER_REQUEST_BRAND_ID_LIST, idList);
		userRequestDTO.setExtProps(extProps);
		return gatewayBrandService.deleteBrandsInBatch(userRequestDTO);
	}

	/*
	 * 查询某个类目的上级类目和上上级类目
	 */
	@GetMapping(value = "/auth/category/parents/{categoryId}")
	public ResultDTO<CategoryDTO> showCategoryFathers(@PathVariable("categoryId") Integer categoryId)
	{
		ResultDTO<CategoryDTO> res = categoryService.findCategoryParents(categoryId);
		if (res.isSuccess())
		{
			return res;
		} else
		{
			return com.joker.library.utils.ResultUtils.fail(res.getMsg());
		}
	}

	/*
	 * 添加或者更新类目
	 */
	@PostMapping(value = "/addOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> addOrUpdateCategory(CategoryFormModel model, @Valid BindingResult result)
	{
		if (result.hasErrors())
		{
			String error = "";
			for (ObjectError e : result.getAllErrors())
			{
				error += e.getDefaultMessage();
			}
			return ResultUtils.fail(error);
		}
		UserDTO user = AdminUtil.getLoginUser();
		if (model.getCategoryId() == null)
		{
			model.setCreator(user.getUsername());
			model.setCreatorUserId(user.getUserId());
		} else
		{
			model.setLastOperator(user.getUsername());
			model.setLastOperatorId(user.getUserId());
		}

		UserRequestDTO userRequestDTO = new UserRequestDTO();
		Map<String, Object> data = new HashMap<>();
		CategoryDTO dto = new CategoryDTO();
		BeanUtils.copyProperties(model, dto);
		// return ResultUtils.sucess();
		data.put(UserRequestConstant.USER_REQUEST_CATEGORY, dto);
		userRequestDTO.setExtProps(data);
		userRequestDTO.setUser(user);
		return categoryService.addOrUpdateCategory(userRequestDTO);
	}

	/*
	 * 显示旗下的所有子类
	 */
	@GetMapping(value = "/category/{categoryId}/childs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> showCategoryAllChilds(@PathVariable("categoryId") Integer categoryId)
	{
		ResultDTO<List<CategoryDTO>> res = categoryService.findCategoryChilds(categoryId);
		return res;
	}

	/*
	 * 显示所有的父类
	 */
	@GetMapping(value = "/category/condition", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> findCategoriesOnCondition(@RequestParam Map<String, Object> condition)
	{
		return categoryService.findCategoriesOnCondition(condition);
	}

	/*
	 * 批量删除类目:
	 */
	@GetMapping(value = "/category/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Integer> deleteCategories(HttpServletRequest request)
	{
		String ids = request.getParameter("ids");
		if (StringUtils.isEmpty(ids))
		{
			return ResultUtils.fail("删除的id不能为空");
		}
		String[] idArr = ids.split(",");
		List<Integer> idList = new ArrayList<>();
		Stream.of(idArr).forEach(i -> {
			idList.add(Integer.parseInt(i));
		});
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUser(AdminUtil.getLoginUser());
		Map<String, Object> props = new HashMap<>();
		props.put(UserRequestConstant.PRODUCT_CATEGORY_DELETE_IDLIST, idList);
		userRequestDTO.setExtProps(props);
		return categoryService.deleteCategories(userRequestDTO);
	}

	@GetMapping(value = "/category/update/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?> changeCategoryStatus(HttpServletRequest request, HttpServletResponse response)
	{
		String categoryIdStr = request.getParameter("categoryId");
		String statusStr = request.getParameter("status");
		if (StringUtils.isEmpty(categoryIdStr))
		{
			return ResultUtils.fail("更改的店铺id不能为空");
		}
		if (StringUtils.isEmpty(statusStr))
		{
			return ResultUtils.fail("修改的状态不能为空");
		}
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUser(AdminUtil.getLoginUser());
		Map<String, Object> props = new HashMap<>();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(Integer.parseInt(categoryIdStr));
		categoryDTO.setStatus(Integer.parseInt(statusStr));
		props.put(UserRequestConstant.PRODUCT_CATEGORY, categoryDTO);
		dto.setExtProps(props);
		return internalCategoryService.updateCategoryStatus(dto);
	}

	/*
	 * 查询所有的brands
	 */
	@GetMapping(value = "/brand/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<BrandDTO>> findAllBrands()
	{
		ResultDTO<List<BrandDTO>> res = innternalStoreService.findAllBrands();
		return res;
	}
	@PostMapping(value="/property/addOrUpdate",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?>addOrUpdateProperty(HttpServletRequest request)
	{
		//如果有propertyId,则表示是更新
		String propertyName = request.getParameter("propertyName");
		String propertyDisSeqStr=StringUtils.defaultString(request.getParameter("propertyDisSeqStr"),"0");
		String propertyIdStr = request.getParameter("propertyId");
		Integer propertyId=null;
		if(!StringUtils.isEmpty(propertyIdStr))
		{
			propertyId=Integer.parseInt(propertyIdStr);
		}
		
		String[] values = request.getParameterValues("propertyValue");
		String[] disSeqValues = request.getParameterValues("propertyValueDisSeq");
		if(values==null || disSeqValues==null||values.length==0||values.length!=disSeqValues.length)
		{
			return ResultUtils.fail("请补足数据,不可提交空数据");
		}
		List<PropertyValueDTO>valueDTOs=new ArrayList<>();
		for(int i=0;i<values.length;i++)
		{
			PropertyValueDTO valueDTO=new PropertyValueDTO();
			valueDTO.setPropertyDisSeq(Integer.parseInt(disSeqValues[i]));
			valueDTO.setPropertyId(propertyId);
			valueDTO.setPropertyValue(values[i]);
			valueDTO.setPropertyValueId(idWorkerService.nextId());
			valueDTOs.add(valueDTO);
		}
		PropertyDTO propertyDTO=new PropertyDTO();
		propertyDTO.setPropertyDisSeq(Integer.parseInt(propertyDisSeqStr));
		propertyDTO.setPropertyName(propertyName);
		propertyDTO.setPropertyId(propertyId);
		propertyDTO.setValues(valueDTOs);
		UserRequestDTO userRequestDTO=new UserRequestDTO();
		userRequestDTO.setUser(AdminUtil.getLoginUser());
		Map<String, Object>params=new HashMap<>();
		params.put(UserRequestConstant.PRODUCT_PROPERTY, propertyDTO);
		userRequestDTO.setExtProps(params);
		return propertyFeignService.addProperty(userRequestDTO);
	}
	@GetMapping(value="/property/{propertyId}/values",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PropertyDTO>showPropertyValues(@PathVariable("propertyId")Integer propertyId)
	{
		ResultDTO<PropertyDTO> resultDTO = internalFacadedService.findPropertyValues(propertyId);
		return resultDTO;
	}
	@GetMapping(value="/property/update",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?>updateProperty(HttpServletRequest request,HttpServletResponse response)
	{
		String deleteIdsStr = request.getParameter("deleteIds");
		String updateIdsStr=request.getParameter("updateIds");
		String propertyIdStr=request.getParameter("propertyId");
		if(StringUtils.isEmpty(propertyIdStr))
		{
			return ResultUtils.fail("属性的id不能为空");
		}
		Integer propertyId=Integer.parseInt(propertyIdStr);
		List<String>updateIdList=new ArrayList<>();
		//如果updateIdStr为空,表明并没有更新操作,全是
		if(!StringUtils.isEmpty(updateIdsStr))
		{
			updateIdList.addAll(Arrays.asList(updateIdsStr.split(",")));
		}
		PropertyDTO propertyDTO=new PropertyDTO();
		String propertyName = request.getParameter("propertyName");
		String propertyDisSeqStr=StringUtils.defaultString(request.getParameter("propertyDisSeqStr"),"0");
		propertyDTO.setPropertyName(propertyName);
		propertyDTO.setPropertyDisSeq(Integer.parseInt(propertyDisSeqStr));
		propertyDTO.setPropertyId(propertyId);
		
		String[] values = request.getParameterValues("propertyValue");
		 String[] valueIds = request.getParameterValues("propertyValueId");
		 String[] proDisSeqs = request.getParameterValues("propertyValueDisSeq");
		 if(valueIds.length!=values.length||valueIds.length!=proDisSeqs.length)
		 {
			 return ResultUtils.fail("参数数目不一致,一个value对应一个id,对应一个排序号");
		 }
		 List<PropertyValueDTO>addOrUpdateDTOs=new ArrayList<>();
		 for(int i=0;i<values.length;i++)
		 {
			 PropertyValueDTO valueDTO=new PropertyValueDTO();
			 valueDTO.setPropertyId(propertyId);
			 valueDTO.setPropertyDisSeq(Integer.parseInt(proDisSeqs[i]));
			 valueDTO.setPropertyValue(values[i]);
			 if(Long.parseLong(valueIds[i])-(-1)==0)
			 {
				 valueDTO.setPropertyValueId(idWorkerService.nextId());
			 }else if(updateIdsStr.contains(valueIds[i]))
			 {
				 valueDTO.setPropertyValueId(Long.parseLong(valueIds[i]));
				 addOrUpdateDTOs.add(valueDTO);
			 }
		 }
		 propertyDTO.setValues(addOrUpdateDTOs);
		 
		 Map<String, Object>extraProps=new HashMap<>();
		 extraProps.put(UserRequestConstant.PRODUCT_PROPERTY, propertyDTO);
		 if(!StringUtils.isEmpty(deleteIdsStr))
		 {
			 extraProps.put(UserRequestConstant.PRODUCT_PROPERTY_DELETEIDS, Arrays.asList(deleteIdsStr.split(",")));
		 }
		 
		 UserRequestDTO dto=new UserRequestDTO();
		 dto.setUser(AdminUtil.getLoginUser());
		 dto.setExtProps(extraProps);
		 return internalFacadedService.updateProperty(dto);
	}

}
