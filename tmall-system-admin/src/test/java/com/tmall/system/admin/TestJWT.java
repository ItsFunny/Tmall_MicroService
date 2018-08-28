/**
*
* @author joker 
* @date 创建时间：2018年8月14日 下午10:53:32
* 
*/
package com.tmall.system.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.utils.JWTUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年8月14日 下午10:53:32
*/
@SpringBootTest(classes=TmallAdminSystem.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJWT
{
	@Autowired
	private JWTUtils jwtUtils;
	
	@Test
	public void test1()
	{
		String string="我爱邢娟";
		AuthTokenDTO tokenDTO=new AuthTokenDTO();
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(string);
		tokenDTO.setData(userDTO);
		tokenDTO.setData(userDTO);
		tokenDTO.setInvalidTime(2000L);
		String token = jwtUtils.buildByAuthPrivateKey(tokenDTO);
		System.out.println(token);
		AuthTokenDTO authTokenDTO = jwtUtils.parseByAuthPublicKey(token);
		String username = authTokenDTO.getData().getUsername();
		System.out.println(username);
	}

}
