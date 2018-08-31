/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午9:26:38
* 
*/
package com.tmall.system.admin.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.netflix.config.MissingConfigurationSourceException;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.system.admin.service.rmi.IBrandService;
import com.tmall.system.admin.util.AdminUtil;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月26日 下午9:26:38
*/
@Controller
@RequestMapping("/admin/product")
public class ProductController 
{
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@RequiresPermissions(value="edit_product_product")
	@RequestMapping("/list")
	public ModelAndView showProducts(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView("products");
		
		return modelAndView;
	}
	@RequiresPermissions(value="edit_product_add")
	@RequestMapping("/add")
	public ModelAndView addProduct(HttpServletRequest request,HttpServletResponse response)
	{
		Map<String, Object>params=new HashMap<String, Object>();
		UserDTO user = AdminUtil.getLoginUser();
		Long storeId = user.getSelfRole().getStoreId();
		Collection<BrandDTO> brands = brandService.findStoreOperatedBrands(storeId);
		params.put("brands", brands);
		
		ModelAndView modelAndView=new ModelAndView("goods_add",params);
		return modelAndView;
	}
	
	
}
