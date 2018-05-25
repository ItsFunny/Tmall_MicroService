/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:41:15
* 
*/
package com.micro.producrt.server.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.micro.product.common.dto.CategoryDTO;

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

	@Select("select category_id,parent_category_id,category_name,create_date,update_date from tamll_category")
	Collection<CategoryDTO> findAll();
}
