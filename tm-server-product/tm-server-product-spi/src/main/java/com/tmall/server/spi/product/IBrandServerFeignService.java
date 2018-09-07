/**
*
* @author joker 
* @date 创建时间：2018年8月17日 下午10:28:12
* 
*/
package com.tmall.server.spi.product;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.config.FeignConfiguration;
import com.tmall.common.dto.BrandDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月17日 下午10:28:12
*/
@FeignClient(name="product",configuration=FeignConfiguration.class)
public interface IBrandServerFeignService
{
	
	@RequestMapping(value="/auth/brand/all",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	Collection<BrandDTO>findAllBrands();
	
	@RequestMapping(value="/auth/brand/store",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<BrandDTO>>findStoreOperatedBrands(@RequestBody Long storeId);
	
	
	@Component
	public static class BrandSererFeignFallbackClass implements IBrandServerFeignService
	{
		@Override
		public Collection<BrandDTO> findAllBrands()
		{
			
			return null;
		}

		@Override
		public ResultDTO<List<BrandDTO>> findStoreOperatedBrands(Long storeId)
		{
			return null;
		}
		
	}
}
