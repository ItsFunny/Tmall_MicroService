/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午2:17:40
* 
*/
package com.tmall.common.other;

import java.util.List;

/**
* 
* 这里最好是有一个abstractService更加好,因为tableName是多余的
* T :model对象
* @author joker 
* @date 创建时间：2018年9月15日 下午2:17:40
*/
public interface ISQLExtentionBaseCRUDService<T>
{
	Integer insert(String tableName, Long hashCodeOrId,T t);
	
	Integer update(String tableName,Number id,T t,Object example);
	
	T findByPrimaryKey(String tableName,Number id);
	
	
	List<T> findByExample(Object object);
	
	
	
}
