/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:41:15
* 
*/
package com.tmall.server.product.dao.db1;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.dao.BaseCategoryDao;
import com.tmall.server.product.dao.TmallCategoryDao;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:41:15
 */
@Component(value="db1CategoryDao")
@Mapper
@Order(1)
public interface Db1CategoryDao extends TmallCategoryDao 
{
	@Select("select count(1) from tmall_category_0")
	Long countCategory();

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,category_display_seq from tmall_category_1")
	Collection<CategoryDTO> findAll();

	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category_1 where category_pid=0 ")
	Collection<CategoryDTO> findTopLevelCategories();
	
	@Select("select category_id,category_pid,category_name,create_date,update_date,category_display_seq from tmall_category_1 where category_pid=#{categoryId}")
	Collection<CategoryDTO> findChildCategoriesWithinOneCategory(Integer categoryId);

	Collection<CategoryDTO> findChildInIDs(Collection<Integer> ids);

	@Select("select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category_1 where parent_category_id in (select category_id from tmall_category_1 where parent_category_id=0) union all select category_id,parent_category_id,category_name,create_date,update_date,display_sequence from tmall_category_1 where parent_category_id=0;")
	Collection<CategoryDTO> findParentCategoryAndChildCategory();

//	long countByExample(TmallCategoryExample example);
//
//	int deleteByExample(TmallCategoryExample example);
//
//	int deleteByPrimaryKey(Integer categoryId);
//
//	int insert(TmallCategory record);
//
//	int insertSelective(TmallCategory record);
//
//	List<TmallCategory> selectByExample(TmallCategoryExample example);
//
//	TmallCategory selectByPrimaryKey(Integer categoryId);
//
//	int updateByExampleSelective(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);
//
//	int updateByExample(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);
//
//	int updateByPrimaryKeySelective(TmallCategory record);
//
//	int updateByPrimaryKey(TmallCategory record);
}
