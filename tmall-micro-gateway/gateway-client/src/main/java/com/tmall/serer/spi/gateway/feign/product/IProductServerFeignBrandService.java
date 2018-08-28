package com.tmall.serer.spi.gateway.feign.product;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;

@FeignClient(name="gateway",fallback=IProductServerFeignBrandService.ProductServerFallbackService.class)
public interface IProductServerFeignBrandService
{
	@GetMapping("/brand/all")
	ResultDTO<Collection<BrandDTO>>findAllBrands();
	
	@Component
	public static class ProductServerFallbackService implements IProductServerFeignBrandService
	{
		@Override
		public ResultDTO<Collection<BrandDTO>> findAllBrands()
		{
			System.out.println("触发了服务降级");
			return ResultUtils.fail("call the fallback service");
		}
	}
}
