/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午4:44:18
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
* @date 创建时间：2018年9月21日 下午4:44:18
*/
@Data
public class SQLExtentionResult<T extends AbstractSQLExtentionModel>
{
	private ISQLExtentionBaseCRUDDao<T>concreteDao;
	private String tableConcreteName;
}
