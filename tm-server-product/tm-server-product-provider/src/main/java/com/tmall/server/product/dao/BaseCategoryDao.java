/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午1:04:59
* 
*/
package com.tmall.server.product.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月14日 下午1:04:59
 */
public interface BaseCategoryDao
{

	@Select("select count(1) form tmall_category")
	Long countCategory();

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,category_display_seq from tmall_category")
	Collection<CategoryDTO> findAll();

	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category where category_pid=0 ")
	Collection<CategoryDTO> findTopLevelCategories();

	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category where category_pid=#{categoryId}")
	Collection<CategoryDTO> findChildCategoriesWithinOneCategory(Integer categoryId);

	Collection<CategoryDTO> findChildInIDs(Collection<Integer> ids);

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category where parent_category_id in (select category_id from tmall_category where parent_category_id=0) union all select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category where parent_category_id=0;")
	Collection<CategoryDTO> findParentCategoryAndChildCategory();

	long countByExample(TmallCategoryExample example);

	int deleteByExample(TmallCategoryExample example);

	int deleteByPrimaryKey(Integer categoryId);

	int insert(TmallCategory record);

	int insertSelective(TmallCategory record);

	List<TmallCategory> selectByExample(TmallCategoryExample example);

	TmallCategory selectByPrimaryKey(Integer categoryId);

	int updateByExampleSelective(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);

	int updateByExample(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);

	int updateByPrimaryKeySelective(TmallCategory record);

	int updateByPrimaryKey(TmallCategory record);
}
