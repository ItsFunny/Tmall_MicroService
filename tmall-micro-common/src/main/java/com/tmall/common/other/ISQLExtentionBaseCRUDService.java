/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午2:17:40
* 
*/
package com.tmall.common.other;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午2:17:40
*/
public interface ISQLExtentionBaseCRUDService
{
	Integer insert(String tableName, Long hashCodeOrId,Object t);
}
