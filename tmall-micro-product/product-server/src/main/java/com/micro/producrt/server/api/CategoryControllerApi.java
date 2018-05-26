/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:32:04
* 
*/
package com.micro.producrt.server.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.producrt.server.productConfig.TmallConfigProperty;
import com.micro.producrt.server.service.ICategoryService;
import com.micro.product.common.dto.CategoryDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.vo.ResultVO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 下午1:32:04
 */
@RestController
public class CategoryControllerApi
{
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private TmallConfigProperty tmallConfigProperty;

	@RequestMapping("/category/all")
	public ResultVO<Collection<CategoryDTO>> findAllCategories()
	{
		Collection<CategoryDTO> all = categoryService.findAll();
		return ResultUtils.sucess(all);
	}

	@RequestMapping("/test")
	public String test()
	{
		System.out.println(tmallConfigProperty.getUrl());
		return tmallConfigProperty.getUrl();
	}

}
