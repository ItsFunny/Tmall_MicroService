/**
*
* @author joker 
* @date 创建时间：2018年8月22日 下午1:50:43
* 
*/
package com.tmall.system.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.server.spi.gateway.store.IGatewayStoreFeignService;

import lombok.extern.slf4j.Slf4j;


/**
* 
* @author joker 
* @date 创建时间：2018年8月22日 下午1:50:43
*/
@Controller
@RequestMapping("/admin/store")
@Slf4j
public class StoreController
{
	@Autowired
	private IGatewayStoreFeignService gatewayStoreFeignService;
	
	@RequiresPermissions(value="edit_store_edit")
	@RequestMapping("/all")
	public ModelAndView showAllStores(HttpServletRequest request,HttpServletResponse response)
	{
		log.info("[showAllStores] 分页显示店铺信息");
		ModelAndView modelAndView=null;
		Map<String, Object>params=new HashMap<String, Object>();
		String pageSizeStr=StringUtils.defaultString(request.getParameter("pageSize"), "1");
		String pageNumStr=StringUtils.defaultString(request.getParameter("pageNum"),"1");
		String status=request.getParameter("status");
		String storeName=request.getParameter("storeName");
		String storeAbbName=request.getParameter("storeAbbName");
		Integer pageSize=Integer.parseInt(pageSizeStr);
		Integer pageNum=Integer.parseInt(pageNumStr);
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		pageRequestDTO.setPageSize(pageSize);
		pageRequestDTO.setPageNum(pageNum);
		if(!StringUtils.isEmpty(storeName))
		{
			pageRequestDTO.getData().put("storeName", storeName);
		}
		if(!StringUtils.isEmpty(storeAbbName))
		{
			pageRequestDTO.getData().put("storeAbbName", storeAbbName);
		}
		if(!StringUtils.isEmpty(status))
		{
			pageRequestDTO.getData().put("status", status);
			params.put("status", status);
		}
		ResultDTO<PageResponseDTO<List<StoreDTO>>> result = gatewayStoreFeignService.findStoresByPage(pageRequestDTO);
		if(result.isSuccess())
		{
			params.put("pageVO", result.getData());
			modelAndView=new ModelAndView("stores",params);
		}else {
			params.put("error", result.getMsg());
			modelAndView=new ModelAndView("error",params);
		}
		return modelAndView;
	}
	/*
	 * 显示待审核商家的列表
	 */
	public ModelAndView showWaitVerifyStores(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		
		
		return modelAndView;
	}
//	@ResponseBody
//	@RequestMapping(value="/test")
//	public ResultDTO<StoreDTO> test()
//	{
//		return gatewayStoreFeignService.findAuthStore("tmall_admin");
//	}
	
}
