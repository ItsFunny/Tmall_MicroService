/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午2:03:19
* 
*/
package com.tmall.common.other;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午2:03:19
*/
public abstract class AbstractSQLExtentionCRUDServie implements ISQLExtentionBaseCRUDService
{	
	@Autowired
	private SQLExtentionHolder holder;

	@Override
	public Integer insert(String tableName, Long hashCodeOrId,Object t)
	{
		SQLExtentionDAOWrapper wrapper = holder.getBaseDao(tableName, hashCodeOrId);
		String concreteName = wrapper.getTableDetailName();
		ISQLExtentionCRUDDao dao = wrapper.getBaseDao();
		return dao.insertSelective(concreteName, t);
	}
	
	
}
