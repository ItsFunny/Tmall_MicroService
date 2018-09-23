/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月23日 上午8:38:00
* 
*/
package com.tmall.server.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.service.impl.CategoryServiceImpl;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月23日 上午8:38:00
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class CategoryMultiDBTest
{
//	@Autowired
//	private CategoryMultiService categoryMultiService;
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Test
	public void testFindByPage()
	{
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		Map<String, Object>data=new HashMap<String, Object>();
		pageRequestDTO.setData(data);
		pageRequestDTO.setPageSize(10);
		pageRequestDTO.setPageNum(3);
		pageRequestDTO.setTablePrefixName("tmall_category");
		PageResponseDTO<List<TmallCategory>> pageResponseDTO = categoryService.findByPage(pageRequestDTO);
		List<TmallCategory> categories = pageResponseDTO.getData();
		for (TmallCategory tmallCategory : categories)
		{
			System.out.println(tmallCategory);
		}
	}
	
}
