/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午12:48:16
* 
*/
package com.tmall.server.spi.gateway.auth;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.common.dto.UserDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月2日 下午12:48:16
 */
@FeignClient(name = "gateway")
public interface IGatewayAuthFeignService
{
	@RequestMapping(value = "/valid/actions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<UserDTO> checkAndGetUserActions(@RequestBody String token);

	@GetMapping(value = "/valid/config/templates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<TmallConfigTemplateDTO>> getConfigTemplates(@RequestParam("map") Map<String, Object> conditions);
}
