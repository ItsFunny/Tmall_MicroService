/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月19日 下午8:48:11
* 
*/
package com.tmall.server.spi.open.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月19日 下午8:48:11
*/
@FeignClient(name="product")
public interface IInternalProductServerCategoryService
{	
	@PostMapping(value="/auth/category/status/update",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<?>updateCategoryStatus(UserRequestDTO dto);

}
