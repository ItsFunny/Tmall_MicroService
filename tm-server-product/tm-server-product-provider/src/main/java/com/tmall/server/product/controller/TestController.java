/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午2:35:56
* 
*/
package com.tmall.server.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.dao.db0.Db0CategoryDao;
import com.tmall.server.product.service.ICategoryService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月14日 下午2:35:56
 */
@RestController
public class TestController
{
	@Autowired
	private Db0CategoryDao categoryDao;
	@Autowired
	private ICategoryService categoryService;

	@RequestMapping("/test")
	public String test()
	{
		Long countCategory = categoryDao.countCategory();
		return "" + countCategory;
	}

	@RequestMapping(value = "/test/page")
	public void testPage()
	{
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		pageRequestDTO.setPageNum(1);
		pageRequestDTO.setPageSize(5);
		PageResponseDTO<List<CategoryDTO>> dto = categoryService.findByPage(pageRequestDTO);
		if (null != dto)
		{
			List<CategoryDTO> categoryDTOs = dto.getData();
			for (CategoryDTO categoryDTO : categoryDTOs)
			{
				System.out.println(categoryDTO);
			}
		}
	}
}
