package com.tmall.server.spi.gateway.product;
//package com.tmall.gateway.client.service;
//
//import java.util.Collection;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.micro.product.common.dto.CategoryDTO;
//import com.tmall.common.vo.ResultVO;
//
//@FeignClient(name = "product", fallback = IProductServerService.IProductServerServiceFallBack.class)
//public interface IProductServerService
//{
//	@GetMapping("/product/category/all")
//	ResultVO<Collection<CategoryDTO>> findAllCategories();
//
//	@Component
//	public static class IProductServerServiceFallBack implements IProductServerService
//	{
//
//		@Override
//		public ResultVO<Collection<CategoryDTO>> findAllCategories()
//		{
//			return null;
//		}
//
//	}
//}
