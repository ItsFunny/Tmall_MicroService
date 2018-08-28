/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午5:39:39
* 
*/
package com.tmall.system.management.service.impl;



import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.dto.ProductDTO;
import com.tmall.server.product.service.IProductServerProductService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午5:39:39
*/
public class ProductServiceImpl implements IProductServerProductService
{
	@Autowired
	private IProductServerProductService productServerProductService;
	@Override
	public Long addOrUpdate(ProductDTO productDTO)
	{
		Long productId = productServerProductService.addOrUpdate(productDTO);
		return productId;
	}

}
