/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:09:36
* 
*/
package com.tmall.server.product.dao.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* 
* @author joker 
* @date 创建时间：2018年6月23日 上午11:09:36
*/
@Mapper
public interface BrandCountDao
{
	@Select("select count(1) from tmall_brand")
	Integer countBrand();

}
