/**
*
* @author joker 
* @date 创建时间：2018年8月5日 下午10:27:59
* 
*/
package com.tmall.server.spi.auth;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.server.spi.auth.impl.AuthFeignFallBackService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月5日 下午10:27:59
 */
@FeignClient(name = "auth", fallback = AuthFeignFallBackService.class)
public interface IAuthFeignService
{
	@RequestMapping(value = "/auth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String> auth(@RequestBody String token);

	@RequestMapping(value="/auth/checkAndGetActions",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<UserDTO>getUseActions(@RequestBody String token);
	
	@GetMapping(value = "/auth/config/template", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<List<TmallConfigTemplateDTO>>getConfigTemplates(@RequestParam("map")Map<String, Object>conditions);
	
	
	@RequestMapping("/test")
	ResultDTO<String> test();

	
}
