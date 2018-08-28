/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:46:14
* 
*/
package com.tmall.server.product.server.service;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmall.common.dto.PropertyDTO;
import com.tmall.server.product.service.IProductServerPropertyService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月24日 下午6:46:14
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertyServiceTest
{
	@Autowired
	private IProductServerPropertyService propertyService;
	@Test
	public void testProperty()
	{
		Collection<PropertyDTO> propertiesInCatIds = propertyService.findPropertiesInCatIds(Arrays.asList(1,2));
		for (PropertyDTO propertyDTO : propertiesInCatIds)
		{
			System.out.println("--------------------");
			System.out.println(propertyDTO);
		}
		
	}

}
