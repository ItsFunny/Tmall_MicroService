/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午4:26:00
* 
*/
package com.joker.library.sqlextention;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月21日 下午4:26:00
 */
public interface ISQLExtentionBaseCRUDDao<T extends AbstractSQLExtentionModel>
{
	Integer insertSelective(T t);

	int insertBatchSelective(@Param("tableName") String tableName, @Param("list") List<T> list);

	int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

	// T selectByExample(Number uniqueKey, Object example);
	List<T> selectByExample(Object example);

	long countByExample(Object example);

	int deleteByExample(Object example);

	int deleteByPrimaryKey(Integer uniquekey);

	int insert(T record);

	// List<T> selectByExample(Object example);
	//
	T selectByPrimaryKey(@Param("tableName") String tableConcreteName, @Param("primaryKey") Number primaryKey);
	//
	// int updateByExampleSelective(@Param("record") T record, @Param("example")
	// Object example);
	//
	// int updateByExample(@Param("record") T record, @Param("example") Object
	// example);
	//
	//// int updateByPrimaryKeySelective(T record);
	//
	// int updateByPrimaryKey(T record);

}
