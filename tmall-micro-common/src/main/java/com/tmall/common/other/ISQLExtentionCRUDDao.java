/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午2:01:31
* 
*/
package com.tmall.common.other;

import java.util.List;

import com.tmall.common.dto.PageExample;

/**
* 
* T 代表的是要插入的数据类型,E代表的对应的Example,因为用的是mybatis,所以要有example
* @author joker 
* @date 创建时间：2018年9月16日 上午9:02:16
*/
public interface ISQLExtentionCRUDDao<T>
{
	Integer insertSelective(String tableName,Object t);
	
	Integer updateSelectTive(String tableName,Object t,Object example);
	
	T findByPrimaryKey(Number id);
	
	List<T>findByExample(Object example);
	
	Integer deleteInBatchByExample(Object example);
	
	

}
