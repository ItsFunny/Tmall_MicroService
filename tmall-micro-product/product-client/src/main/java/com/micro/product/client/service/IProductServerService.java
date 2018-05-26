/**
*
* @author joker 
* @date 创建时间：2018年5月26日 上午11:54:01
* 
*/
package com.micro.product.client.service;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.micro.product.common.dto.CategoryDTO;
import com.tmall.common.vo.ResultVO;

/**
* 
* @author joker 
* @date 创建时间：2018年5月26日 上午11:54:01
*/
@FeignClient(name="category")
public interface IProductServerService
{
	@GetMapping("/category/all")
	ResultVO<Collection<CategoryDTO>>findAllCategories();
}
