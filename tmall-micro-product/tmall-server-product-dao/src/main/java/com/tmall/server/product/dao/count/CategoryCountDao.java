/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午11:20:43
* 
*/
package com.tmall.server.product.dao.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 上午11:20:43
*/
@Mapper
public interface CategoryCountDao
{
	@Select("select count(1) from tmall_category where category_pid=0")
	Integer countTopLevelCategory();
	
	@Select("select count(1) from tmall_category where category_pid=#{categoryId}")
	Integer countChildCategory(Integer categoryId);
}
