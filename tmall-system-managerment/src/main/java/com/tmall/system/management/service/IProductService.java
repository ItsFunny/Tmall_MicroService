/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午5:39:08
* 
*/
package com.tmall.system.management.service;

import com.tmall.common.dto.ProductDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午5:39:08
*/
public interface IProductService
{
	Integer addOrUpdate(ProductDTO productDTO);
}
