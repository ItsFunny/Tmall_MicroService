/**
*
* @author joker 
* @date 创建时间：2018年8月28日 下午12:20:06
* 
*/
package com.tmall.gateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.dto.ResultDTO;
import com.tmall.server.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月28日 下午12:20:06
 */
@RestController
public class LoginController
{
	@Autowired
	private IFacadedService facadedService;
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> userLogin(@RequestParam("loginKey") String loginKey,
			@RequestParam("password") String password, @RequestParam("storeAbbName") String storeAbbName)
	{
		return facadedService.loginAndAuth(loginKey, password, storeAbbName);
	}

}
