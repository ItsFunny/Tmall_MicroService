/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午10:08:52
* 
*/
package com.tmall.server.gateway.provider.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午10:08:52
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CategoryWEBTest
{
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testPage() throws Exception
	{
		ResultActions actions = this.mockMvc.perform(get("/valid/category/show")).andDo(print()).andExpect(status().isOk());
		System.out.println(actions);
		
	}

}
