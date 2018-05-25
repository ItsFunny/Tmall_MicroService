/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:41:15
* 
*/
package com.micro.producrt.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* 
* @author joker 
* @date 创建时间：2018年5月25日 上午10:41:15
*/
@Mapper
public interface CategoryDao
{
	@Select("select count(1) form tmall_category")
	Integer countCategory();
	
}
