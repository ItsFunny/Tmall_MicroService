/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午1:56:12
* 
*/
package com.tmall.server.product.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tmall.common.dto.ProductDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月25日 下午1:56:12
*/
@Mapper
public interface ProductDao
{
	Long addProduct(ProductDTO productDTO);
}
