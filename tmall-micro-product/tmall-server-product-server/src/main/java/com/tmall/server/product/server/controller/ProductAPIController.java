/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午1:33:22
* 
*/
package com.tmall.server.product.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.dto.ProductDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.product.service.IProductServerProductService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月13日 下午1:33:22
*/
@RestController
@RequestMapping("/product")
public class ProductAPIController
{
	@Autowired
	private IProductServerProductService productService;

	/*
	 * 这个是外抛的api接口
	 */
	@RequestMapping("/add")
	public ResultDTO<Long> addProduct(@RequestBody ProductDTO productDTO)
	{
		//这里还需要记录下日志
		Long productId = productService.addOrUpdate(productDTO);
		return ResultUtils.sucess(productId);
	}
}
