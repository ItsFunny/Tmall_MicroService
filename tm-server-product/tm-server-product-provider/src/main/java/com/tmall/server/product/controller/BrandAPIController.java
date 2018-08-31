package com.tmall.server.product.controller;
///**
//*
//* @author joker 
//* @date 创建时间：2018年6月23日 上午11:43:03
//* 
//*/
//package com.tm.server.product.controller;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tmall.common.dto.BrandDTO;
//import com.tmall.common.dto.ResultDTO;
//import com.tmall.common.exception.TmallBussinessException;
//import com.tmall.common.utils.ResultUtils;
//import com.tmall.server.product.service.IBrandService;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年6月23日 上午11:43:03
//*/
//@RestController
//@RequestMapping("/auth/brand")
//@CrossOrigin(value= {
//		"http://localhost:9001/"
//})
//public class BrandAPIController
//{
//	@Autowired
//	private IBrandService brandService;
//	
//	
//	
//	//显示所有的品牌
//	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResultDTO<Collection<BrandDTO>>findALlBrands()
//	{
//		try
//		{
//			return ResultUtils.sucess(brandService.findAllBrands());
//		} catch (Exception e)
//		{
//			return ResultUtils.fail(e.getMessage());
//		}
//	}
//	//显示某个商家合作的店铺
//	@RequestMapping(value="/store")
//	public ResultDTO<Collection<BrandDTO>>findStoreOperatedBrands(@RequestBody Long storeId)
//	{
//		if(storeId<0)
//		{
//			throw new TmallBussinessException(TmallBussinessException.ILLEGAL_ARGUMETN_EXCEPTION, "参数storeId不可小于0");
//		}
//		List<BrandDTO> brands = brandService.findStoreOperatedBrands(storeId);
//		return ResultUtils.sucess(brands);
//	}
//}
