/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午11:10:38
* 
*/
package com.tmall.system.management.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.ProductDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.PicTypeEnum;
import com.tmall.common.enums.RestAPIStatus;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.product.service.IProductServerProductService;
import com.tmall.server.spi.service.product.IProductServerFeignBrandService;
import com.tmall.system.management.service.IProductService;
import com.tmall.system.management.utils.ManagementUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月22日 上午11:10:38
 */
@Controller
@RequestMapping("/product")
public class ProductController
{
	// @Autowired
	// private ICategoryServerFeignService categoryServerFeignService;
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private IProductServerFeignBrandService brandService;

	@Autowired
	private IProductServerProductService productService;

	@RequestMapping("/add")
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = null;
		ResultDTO<Collection<BrandDTO>> resultDTO = brandService.findAllBrands();
		if (resultDTO.getCode().equals(RestAPIStatus.SUCESS.ordinal()))
		{
			modelAndView = new ModelAndView("product-add");
			System.out.println(resultDTO.getData().size());
			modelAndView.addObject("brands", resultDTO.getData());
		} else
		{
			modelAndView = new ModelAndView("error2");
			modelAndView.addObject("error", resultDTO.getMsg());
		}
		return modelAndView;
	}
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println(request.getServletPath());
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(request.getServletContext().getRealPath("/resources/static/pics"));
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("static"));
		ModelAndView modelAndView=new ModelAndView("test");
		return modelAndView;
	}
	@RequestMapping("/upload")
	public ModelAndView doTest(@RequestParam(value="pictureFile",required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException
	{
		ServletInputStream inputStream = request.getInputStream();
		ModelAndView modelAndView=new ModelAndView("test");
		modelAndView.addObject("error","add sucess");
		return modelAndView;
	}
}
