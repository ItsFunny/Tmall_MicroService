/**
*
* @author joker 
* @date 创建时间：2018年9月4日 下午2:26:14
* 
*/
package com.tmall.server.spi.gateway.brand;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 下午2:26:14
 */
@FeignClient(name = "gateway")
public interface IGatewayBrandService
{
	@PostMapping(value = "/valid/brand/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String> addOrUpdateBrand(@RequestBody UserRequestDTO userRequestDTO);
	
	
	@GetMapping(value="/valid/brand/{brandId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<BrandDTO>findTypeByTypeId(@PathVariable("brandId")Integer brandId);
	
	//批量删除品牌
	@PostMapping(value="/valid/brand/delete",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>deleteBrandsInBatch(@RequestBody UserRequestDTO userRequestDTO);
}
