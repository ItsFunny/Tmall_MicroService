/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午5:44:16
* 
*/
package com.tmall.server.spi.gateway.property;

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
 * @date 创建时间：2018年9月22日 下午5:44:16
 */
@FeignClient(name = "gateway")
public interface IGatewayProductServerPropertyFeignService
{
	@PostMapping(value = "/valid/property/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<?> addProperty(@RequestBody UserRequestDTO dto);

	@PostMapping(value = "/valid/property/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<PropertyDTO>>> findPropertiesByCondition(@RequestBody PageRequestDTO pageRequestDTO);
}
