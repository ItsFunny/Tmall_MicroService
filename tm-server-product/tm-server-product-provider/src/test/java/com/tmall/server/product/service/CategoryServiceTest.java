/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午6:38:58
* 
*/
package com.tmall.server.product.service;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.TmallProductServerApplication;
import com.tmall.server.product.config.ProductAPPServerConfiguraiton;
import com.tmall.server.product.service.ICategoryService;
import com.tmall.server.product.service.impl.CategoryServiceImpl;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午6:38:58
*/
@SpringBootTest(classes= {TmallProductServerApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class CategoryServiceTest
{
	@Autowired
	private ICategoryService categoryService;
	
	@Test
	public void testPageFirstPageNum()
	{
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		pageRequestDTO.setPageNum(1);
		pageRequestDTO.setPageSize(5);
		PageResponseDTO<List<CategoryDTO>> dto = categoryService.findByPage(pageRequestDTO);
		if(null!=dto)
		{
			List<CategoryDTO> categoryDTOs = dto.getData();
			for (CategoryDTO categoryDTO : categoryDTOs)
			{
				System.out.println(categoryDTO);
			}
		}
	}
	@Test
	public void testRandomPageNum()
	{
		Integer pageSize=new Random(47).nextInt(30);
		Integer pageNum=new Random(47).nextInt(2044/pageSize);
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		pageRequestDTO.setPageNum(pageNum);
		pageRequestDTO.setPageSize(pageSize);
		PageResponseDTO<List<CategoryDTO>> dto = categoryService.findByPage(pageRequestDTO);
		if(null!=dto)
		{
			List<CategoryDTO> categoryDTOs = dto.getData();
			for (CategoryDTO categoryDTO : categoryDTOs)
			{
				System.out.println(categoryDTO);
			}
		}
	}
	@Test
	public void testOtherPageNum()
	{
		PageRequestDTO pageRequestDTO=new PageRequestDTO();
		pageRequestDTO.setPageNum(5);
		pageRequestDTO.setPageSize(10);
		PageResponseDTO<List<CategoryDTO>> dto = categoryService.findByPage(pageRequestDTO);
		if(null!=dto)
		{
			List<CategoryDTO> categoryDTOs = dto.getData();
			for (CategoryDTO categoryDTO : categoryDTOs)
			{
				System.out.println(categoryDTO);
			}
		}
	}
	@Test
	public void testFindbById()
	{
		Integer id=1;
		CategoryDTO categoryDTO = categoryService.findByCategoryId(id);
		System.out.println(categoryDTO);
	}
	

}
