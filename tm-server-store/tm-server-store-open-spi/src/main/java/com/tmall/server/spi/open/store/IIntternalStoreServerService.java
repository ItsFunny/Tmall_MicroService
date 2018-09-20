/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午3:07:12
* 
*/
package com.tmall.server.spi.open.store;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 下午3:07:12
*/

@FeignClient(name="store")
public interface IIntternalStoreServerService
{
	@GetMapping(value="/auth/brand/allBrands",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<BrandDTO>>findAllBrands();
	
}
