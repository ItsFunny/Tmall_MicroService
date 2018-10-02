/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午2:43:52
* 
*/
package com.tmall.system.admin.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEventPublisher;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.server.spi.gateway.IGatewayBrandFeignService;

import antlr.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午2:43:52
 */
@Controller
@RequestMapping("/admin/brand")
public class BrandController
{
	@Autowired
	private IGatewayBrandFeignService gatewayBrandFeignService;
	@Autowired
	private AppEventPublisher eventPublisher;
	
	/*
	 * 删除或者添加品牌
	 */
	
//	@Bean
//	public MapperScannerConfigurer mappperScannerConfigurer()
//	{
//		MapperScannerConfigurer configurer=new MapperScannerConfigurer();
//		configurer.setBasePackage("");
//		return configurer;
//	}
	@RequiresPermissions(value =
	{ "edit_product_brand" })
	@RequestMapping("/all")
	public ModelAndView showAllBrands(
			@RequestParam(required = false, name = "pageSize", defaultValue = "10") String pageSizeStr,
			@RequestParam(required = false, name = "pageNum", defaultValue = "1") String pageNumStr,
			HttpServletRequest request, HttpServletResponse response)
	{

		ModelAndView modelAndView = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<>();
		Integer pageSize = Integer.parseInt(pageSizeStr);
		Integer pageNum = Integer.parseInt(pageNumStr);
		String storeIdStr = request.getParameter("storeId");
		if (!org.apache.commons.lang3.StringUtils.isEmpty(storeIdStr))
		{
			condition.put("storeId", storeIdStr);
		}
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		pageRequestDTO.setPageSize(pageSize);
		pageRequestDTO.setPageNum(pageNum);
		ResultDTO<PageResponseDTO<List<BrandDTO>>> resultDTO = gatewayBrandFeignService
				.findBrandsByPage(pageRequestDTO);
		if (resultDTO.isSuccess())
		{
			
//			BeanFactory beanFactory=new ClassPathXmlApplicationContext(configLocation)
//			ApplicationContext context=new AnnotationConfigApplicationContext(annotatedClasses)
			params.put("pageVO", resultDTO.getData());
			modelAndView = new ModelAndView("brands", params);
		} else
		{
			params.put("error", resultDTO.getMsg());
			modelAndView = new ModelAndView("error", params);
		}
		condition = null;
		return modelAndView;
	}
	public static void main(String[] args)
	{
		System.out.println(3&5);
	}
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
	}
	
}
