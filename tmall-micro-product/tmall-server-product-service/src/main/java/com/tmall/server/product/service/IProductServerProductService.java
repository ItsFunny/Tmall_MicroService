/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午2:31:16
* 
*/
package com.tmall.server.product.service;

import com.tmall.common.dto.ProductDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午2:31:16
*/
public interface IProductServerProductService
{
	Long addOrUpdate(ProductDTO productDTO);
}
