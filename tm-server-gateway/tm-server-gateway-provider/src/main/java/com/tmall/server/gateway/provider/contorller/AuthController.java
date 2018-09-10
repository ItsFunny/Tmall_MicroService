/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午12:45:47
* 
*/
package com.tmall.server.gateway.provider.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.facade.service.IAuthService;
import com.tmall.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月2日 下午12:45:47
 */
@RestController
@RequestMapping("/valid")
public class AuthController
{
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IFacadedService facadedService;

	// 获取用户的权限等相关信息
	@RequestMapping(value = "/actions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<UserDTO> getUserActions(@RequestBody String token)
	{
		return authService.getUserActions(token);
	}
	//获取系统config的相关模板
	@GetMapping(value="/config/templates",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<TmallConfigTemplateDTO>>getConfigTemplates(@RequestParam("map")Map<String, Object>conditions)
	{
		return facadedService.getConfigTemplates(conditions);
	}
}
