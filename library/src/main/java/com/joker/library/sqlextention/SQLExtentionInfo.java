/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午3:08:23
* 
*/
package com.joker.library.sqlextention;

import java.util.List;


import lombok.Data;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月21日 下午3:08:23
 */
@Data
public class SQLExtentionInfo<T extends AbstractSQLExtentionModel>
{
	private String tablePrefixName;

	private ISQLExtentionProxyBaseCRUDDao<T> proxyDao;
	
	private String proxyDaoBeanName;

	private DBInfo<T>[] dbs;

	private Integer totalTables;

	public Integer getTotalTableCounts()
	{
		if (totalTables == null)
		{
			totalTables = 0;
			for (DBInfo<T> dbInfo : dbs)
			{
				totalTables += dbInfo.getTableCounts();
			}
		}
		return totalTables;
	}

	public void setDbs(DBInfo<?>[] dbs)
	{
		this.dbs = (DBInfo<T>[]) dbs;
	}

	public void setProxyDao(ISQLExtentionProxyBaseCRUDDao<?> dao)
	{
		this.proxyDao = (ISQLExtentionProxyBaseCRUDDao<T>) dao;
	}

	@Data
	public static class DBInfo<T extends AbstractSQLExtentionModel>
	{
		private Integer tableCounts;
		private TableInfo<T>[] tables;
		private ISQLExtentionBaseCRUDDao<T> dao;
		private String daoBeanName;
		
		public void setDao(ISQLExtentionBaseCRUDDao<?> dao)
		{
			this.dao=(ISQLExtentionBaseCRUDDao<T>) dao;
		}

		public void setTables(TableInfo<?>[] infos)
		{
			this.tables = (TableInfo<T>[]) infos;
		}
	}

	@Data
	public static class TableInfo<T>
	{
		private String tableName;

	}
}
