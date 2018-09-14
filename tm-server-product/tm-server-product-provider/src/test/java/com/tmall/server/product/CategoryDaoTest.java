/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午2:14:52
* 
*/
package com.tmall.server.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmall.server.product.TmallProductServerApplication;
import com.tmall.server.product.config.ProductAPPServerConfiguraiton;
import com.tmall.server.product.dao.db0.Db0CategoryDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午2:14:52
*/
@SpringBootTest(classes= {TmallProductServerApplication.class,ProductAPPServerConfiguraiton.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class CategoryDaoTest
{
	@Autowired
	private Db0CategoryDao categoryDao;
	
	@Test
	public void testCountAll()
	{
		Long cLong = categoryDao.countCategory();
		System.out.println(cLong);
	}
}
