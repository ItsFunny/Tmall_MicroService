/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月23日 下午8:22:09
* 
*/
package com.tmall.server.spi.open.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月23日 下午8:22:09
*/
@FeignClient(name="product")
public interface IInternalProductServerPropertyService
{
	@GetMapping(value="/auth/property/{propertyId}/values",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PropertyDTO>findPropertyValues(@PathVariable("propertyId")Integer propertyId);
	
	@PostMapping(value="/auth/property/update",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<?>updateProperty(@RequestBody UserRequestDTO userRequestDTO);
	
	

}
