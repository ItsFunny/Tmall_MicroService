/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:59:50
* 
*/
package com.tmall.server.facade.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.utils.JWTUtils;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.facade.service.ILoginService;
import com.tmall.server.spi.auth.IAuthFeignService;
import com.tmall.server.spi.user.IUserFeignService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月28日 上午9:59:50
 */
@Service
public class LoginServiceImpl implements ILoginService
{
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private IUserFeignService userFeignService;
	@Autowired
	private IAuthFeignService authFeignService;

	@Override
	public ResultDTO<String> loginAndAuth(String loginKey, String password, String storeAbbName)
	{
		ResultDTO<UserDTO> resultDTO = login(loginKey, password, storeAbbName);
		if (resultDTO.isSuccess())
		{
			UserDTO userDTO = resultDTO.getData();
			AuthTokenDTO authTokenDTO = new AuthTokenDTO();
			authTokenDTO.setData(userDTO);
			authTokenDTO.setStoreAbbName(storeAbbName);
			String token = jwtUtils.buildBySSOPrivateKey(authTokenDTO);
			return getAuth(token);
		} else
		{
			return ResultUtils.fail(resultDTO.getMsg());
		}
	}

	@Override
	public ResultDTO<String> getAuth(String json)
	{
		return authFeignService.auth(json);
	}

	@Override
	public ResultDTO<UserDTO> login(String loginKey, String password, String storeAbbName)
	{
		Map<String, Object> lParam = new HashMap<>();
		lParam.put("loginKey", loginKey);
		lParam.put("password", password);
		lParam.put("storeAbbName", storeAbbName);
		Long invalidTime = System.currentTimeMillis() + 1000 * 60 * 5L;
		lParam.put(AuthConstant.INVALID_TIME, invalidTime);
		String reqJson = jwtUtils.buildBySSOPrivateKey(lParam);
		ResultDTO<UserDTO> loginRes = userFeignService.login(reqJson);
		return loginRes;
	}

}
