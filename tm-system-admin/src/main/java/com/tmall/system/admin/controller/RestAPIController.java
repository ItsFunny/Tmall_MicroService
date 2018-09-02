/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午9:54:00
* 
*/
package com.tmall.system.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.enums.StoreStatusEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.facade.service.IFacadedService;
import com.tmall.server.spi.gateway.store.IGatewayStoreFeignService;
import com.tmall.server.spi.store.IStoreServerFeignService;
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
	private IStoreServerFeignService storeServerFeignService;

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
}
