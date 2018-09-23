/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午4:17:54
* 
*/
package com.tmall.server.spi.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午4:17:54
 */
@FeignClient(name = "product")
public interface IProductServerPropertyFeignService
{
	@PostMapping(value = "/auth/property/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<?> addPropertyAndValues(@RequestBody UserRequestDTO userRequestDTO);

	@PostMapping(value = "/auth/property/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<PropertyDTO>>> findPropertiesByCondition(@RequestBody PageRequestDTO pageRequestDTO);

}
