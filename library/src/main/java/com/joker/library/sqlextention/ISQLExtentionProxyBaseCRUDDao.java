/**
*
* @Descriptiondddddddddddddddddddddddd
* @author joker 
* @date 创建时间：2018年9月21日 下午3:13:12
* 
*/
package com.joker.library.sqlextention;

import java.util.List;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午3:13:12
*/
public interface ISQLExtentionProxyBaseCRUDDao<T extends AbstractSQLExtentionModel> extends ISQLExtentionBaseCRUDDao<T>
{
	ISQLExtentionBaseCRUDDao<T> getDetailConfigDao(Number uniqueKey);
	List<? extends ISQLExtentionBaseCRUDDao<T>> getAllDaos();
	
}
