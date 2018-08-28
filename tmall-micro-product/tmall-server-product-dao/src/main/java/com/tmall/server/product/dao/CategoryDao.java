/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:41:15
* 
*/
package com.tmall.server.product.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tmall.common.dto.CategoryDTO;


/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:41:15
 */
@Mapper
public interface CategoryDao extends TmallCateogryDao
{
	@Select("select count(1) form tmall_category")
	Integer countCategory();

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,category_display_seq from tmall_category")
	Collection<CategoryDTO> findAll();

	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category where category_pid=0 ")
	Collection<CategoryDTO> findTopLevelCategories();

	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category where category_pid=#{categoryId}")
	Collection<CategoryDTO> findChildCategoriesWithinOneCategory(Integer categoryId);

	Collection<CategoryDTO> findChildInIDs(Collection<Integer> ids);

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category where parent_category_id in (select category_id from tmall_category where parent_category_id=0) union all select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category where parent_category_id=0;")
	Collection<CategoryDTO> findParentCategoryAndChildCategory();

}
