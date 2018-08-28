/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午10:27:46
* 
*/
package com.tmall.server.user.server.service;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmall.common.dto.UserDTO;
import com.tmall.server.user.service.IUserService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月13日 下午10:27:46
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
{
	@Autowired
	private IUserService userService;
	@Test
	public void testUpdateinfo() throws SQLException
	{
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername("qqq");
		userDTO.setEmail("128318293@163.com");
		userDTO.setIDCard("33993129389123");
		userDTO.setMobile("13868938743");
		userDTO.setUserId(2l);
		userService.updateUser(userDTO);
	}
	@Test
	public void testUpdatePassword()
	{
		userService.updateUserPassword("123456", "123", 2l);
	}

}
