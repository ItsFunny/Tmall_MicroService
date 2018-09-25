/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午4:53:53
* 
*/
package com.joker.library.sqlextention;

import lombok.Data;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午4:53:53
*/
@Data
public class SQLExtentionDaoWrapper<T extends AbstractSQLExtentionModel> 
{
	private ISQLExtentionBaseCRUDDao<T>dao;
	private String tableName;
	private Integer tableCounts;
	
	public void setDao(ISQLExtentionBaseCRUDDao<?> dao)
	{
		this.dao=(ISQLExtentionBaseCRUDDao<T>) dao;
	}

}
