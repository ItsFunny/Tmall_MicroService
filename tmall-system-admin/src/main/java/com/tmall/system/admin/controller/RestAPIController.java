/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午9:54:00
* 
*/
package com.tmall.system.admin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.utils.ResultUtils;
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

	@Autowired
	private IProductServerCategoryFeignService categoryFeignSerivce;
	
	@Autowired
	private IFacadedService facadedService;

	// 显示商家下的所有类目
	@RequestMapping(value = "/category/topLevel/all", method =
	{ RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> findCategoriesAllTopLevel()
	{
		try
		{
			Long storeId = AdminUtil.getLoginUser().getSelfRole().getStoreId();
			ResultDTO<List<CategoryDTO>> resultDTO = categoryFeignSerivce.findStoreAllTopLevelCategories(storeId);
			return resultDTO;
		} catch (Exception e)
		{
			return ResultUtils.fail(e.getMessage());
		}
	}
	@RequestMapping(value = "/category/child/{parentCategoryId}", method =
	{ RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<CategoryDTO>> findALlChilds(@PathVariable Integer parentCategoryId)
	{
		try
		{
			return categoryFeignSerivce.findCateogryChilds(parentCategoryId);
		} catch (Exception e)
		{
			return ResultUtils.fail();
		}
	}
	@RequiresPermissions("edit_store_show_detail")
	@RequestMapping(value="/store/detail/{storeId}")
	public ResultDTO<StoreDTO>showStoreDetail(@PathVariable("storeId")Long storeId)
	{
		//调用store服务的接口  ,,这里记得改为通过zuul调用
		return facadedService.findStoreDetail(storeId);
	}
	
	@RequiresPermissions("edit_store_update_status")
	@RequestMapping(value="/store/updateStatus/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String>updateStoreStatus(@PathVariable("storeId")Long storeId)
	{
		//修改状态,通过zuul调用接口
		
		
		
		
		
		return ResultUtils.fail();
	}

}
