/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午2:31:39
* 
*/
package com.tmall.server.product.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.ProductDTO;
import com.tmall.server.product.dao.ProductDao;
import com.tmall.server.product.service.IProductServerProductService;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午2:31:39
*/
@Service
public class ProductServiceImpl implements IProductServerProductService
{
	@Autowired
	private ProductDao productDao;
	@Override
	public Long addOrUpdate(ProductDTO productDTO)
	{
		productDTO.setUpdateDate(new Date());
		Long productId = productDao.addProduct(productDTO);
		return productId;
	}
	
}
