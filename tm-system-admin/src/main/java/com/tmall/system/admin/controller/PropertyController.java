/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:31:47
* 
*/
package com.tmall.system.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.server.spi.gateway.property.IGatewayProductServerPropertyFeignService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 上午10:31:47
 */
@Controller
@RequestMapping(value = "/admin/property")
public class PropertyController
{
	@Autowired
	private IGatewayProductServerPropertyFeignService propertyFeignService;

	// 这里需要再添加权限
	@RequestMapping(value = "/show")
	public ModelAndView showAllSPU(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute @RequestParam(name = "pageSize", defaultValue = "10") String pageSizeStr,
			@ModelAttribute @RequestParam(name = "pageNum", defaultValue = "1") String pageNumStr,
			@ModelAttribute @RequestParam(name="searchKey",required=false)String searchKey)
	{
		ModelAndView modelAndView = null;
		Map<String, Object>params=new HashMap<>();
		Map<String, Object>condition=new HashMap<>();
		// 分页查询所有的规格选项
		// 从有到无,因此先从添加开始做起
		int pageSize=Integer.parseInt(pageSizeStr);
		int pageNum=Integer.parseInt(pageNumStr);
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		pageRequestDTO.setTablePrefixName(SQLExtentionConstant.PROPERTY);
		pageRequestDTO.setPageNum(pageNum);
		pageRequestDTO.setPageSize(pageSize);
		try
		{
			Number uniqueKey=Long.parseLong(searchKey);
			pageRequestDTO.setSingal(true);
			pageRequestDTO.setSingleKey(uniqueKey);
		} catch (Exception e)
		{
			condition.put("searchKey", searchKey);
		}
		pageRequestDTO.setData(condition);
		ResultDTO<PageResponseDTO<List<PropertyDTO>>> res = propertyFeignService.findPropertiesByCondition(pageRequestDTO);
		if(!res.isSuccess())
		{
			modelAndView=new ModelAndView("error");
			modelAndView.addObject("error",res.getMsg());
		}else {
			params.put("pageVO", res.getData());
			modelAndView = new ModelAndView("properties", params);
		}

		// 如何拼接propertyDTO呢
		// 前台全部以string 的形式上传,以某种格式分隔

		return modelAndView;
	}

}
