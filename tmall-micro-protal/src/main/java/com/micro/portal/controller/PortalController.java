/**
*
* @author joker 
* @date 创建时间：2018年5月27日 上午10:53:11
* 
*/
package com.micro.portal.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.micro.portal.facaded.IIndexServiceFacaded;
import com.micro.portal.model.PortalCategory;

/**
* 显示首页
* @author joker 
* @date 创建时间：2018年5月27日 上午10:53:11
*/
@Controller
public class PortalController
{
	@Autowired
	private IIndexServiceFacaded indexSerivceFacaded;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=null;
		Collection<PortalCategory> categories = indexSerivceFacaded.showCategories();
		modelAndView=new ModelAndView("index");
		modelAndView.addObject("categories",categories);
		return modelAndView;
	}
	@RequestMapping("/ftl/test/map")
	public ModelAndView test()
	{
		ModelAndView modelAndView=null;
//		
//		CategoryDTO categoryKey=new CategoryDTO();
//		categoryKey.setCategoryName("key1");
//		categoryKey.setCategoryId(1);
//		Map<CategoryDTO, List<CategoryDTO>>	map=new HashMap<CategoryDTO, List<CategoryDTO>>();
//		CategoryDTO categoryDTO=new CategoryDTO();
//		categoryDTO.setCategoryName("name1");
//		List<CategoryDTO>list=new ArrayList<CategoryDTO>();
//		list.add(categoryDTO);
//		CategoryDTO categoryDTO2=new CategoryDTO();
//		categoryDTO2.setCategoryName("name2");
//		list.add(categoryDTO2);
//		map.put(categoryKey, list);
		
		
//		Map<CategoryDTO, String >map=new HashMap<>();
//		map.put(categoryKey, "1");
		
		
		Map<Integer, String>map=new HashMap<>();
		map.put(1, "qwe");
		
		modelAndView=new ModelAndView("test");
		modelAndView.addObject("map",map);
		return modelAndView;
	}
	@RequestMapping("/ftl/test/import")
	public ModelAndView test(HttpServletRequest request,HttpServletResponse responses)
	{
		ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;
	}
//	@RequestMapping("/ftl/test/map")
//	@ResponseBody
//	public  Map<CategoryDTO, List<CategoryDTO>> test2()
//	{
//		return indexSerivceFacaded.showCategories();
//	}
//	
}
