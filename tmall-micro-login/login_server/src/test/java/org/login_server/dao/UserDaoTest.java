package org.login_server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.login_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest
{
	@Autowired
	private UserDao userDao;

	@Test
	public void testFindByEmail()
	{
		
		User user = userDao.findByEmail(0, "18757883747@163.com");
		System.out.println(user);
		
	}

}
