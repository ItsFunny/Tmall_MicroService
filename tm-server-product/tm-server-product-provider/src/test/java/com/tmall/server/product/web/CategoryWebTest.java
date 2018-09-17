/**
*
* @author joker 
* @date 创建时间：2018年9月17日 上午10:40:52
* 
*/
package com.tmall.server.product.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.tmall.server.product.TmallProductServerApplication;

/**
* 
* @author joker 
* @date 创建时间：2018年9月17日 上午10:40:52
*/
@SpringBootTest(classes= {TmallProductServerApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CategoryWebTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testFindChilds()
	{

		try
		{
			ResultActions actions = mockMvc.perform(get("/auth/category/1/childs").accept(MediaType.APPLICATION_JSON_UTF8_VALUE));
			System.out.println(actions);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
