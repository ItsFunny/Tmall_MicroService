/**
*
* @author joker 
* @date 创建时间：2018年5月26日 上午11:54:01
* 
*/
package com.tmall.server.spi.product;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年5月26日 上午11:54:01
*/
@FeignClient(name="product",fallback=IProductServerFeignService.IProductServerServiceFallBack.class)
public interface IProductServerFeignService
{
	@GetMapping("/category/all")
	ResultDTO<Collection<CategoryDTO>>findAllCategories();
	
	@Component
	public static class IProductServerServiceFallBack implements IProductServerFeignService
	{
		@Override
		public ResultDTO<Collection<CategoryDTO>> findAllCategories()
		{
			System.out.println("调用fallback函数");
			return ResultUtils.fail();
		}
	}
}
