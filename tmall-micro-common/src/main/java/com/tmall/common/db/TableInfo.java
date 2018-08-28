/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午12:16:47
* 
*/
package com.tmall.common.db;

import java.util.HashMap;
import java.util.Map;

/**
* 
* @author joker 
* @date 创建时间：2018年6月21日 下午12:16:47
*/
public class TableInfo
{
	private Map<String, Integer>tableStorage=new HashMap<>();
	private static TableInfo tableInfo=new TableInfo();
	private TableInfo()
	{
		
	}
	public synchronized Integer getTotalCount(String key)
	{
		return tableStorage.get(key);
	}
	public synchronized void pub(String tableName,Integer totalCount)
	{
		this.tableStorage.put(tableName, totalCount);
	}
	public static TableInfo getTableInfo()
	{
		return tableInfo;
	}
}
