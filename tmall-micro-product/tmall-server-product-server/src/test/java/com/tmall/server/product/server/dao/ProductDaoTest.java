/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午2:06:14
* 
*/
package com.tmall.server.product.server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmall.common.dto.ProductDTO;
import com.tmall.server.product.dao.ProductDao;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午2:06:14
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductDaoTest
{
	@Autowired
	private ProductDao productDao;
	@Test
	public void addTest()
	{
		ProductDTO productDTO=new ProductDTO();
		productDTO.setCategoryId(1);
		productDTO.setBrandId(1);
		productDTO.setProductName("test1");
		productDTO.setProductDesc("qweqw");
		Long res = productDao.addProduct(productDTO);
		System.out.println(res);
	}

}
