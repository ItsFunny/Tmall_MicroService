/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月24日 下午12:23:03
* 
*/
package com.joker.library.sqlextention;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;


/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月24日 下午12:23:03
 */
public abstract class AbstractSQLExtentionpProxyBaseCRUDDao<T extends AbstractSQLExtentionModel>
		implements ISQLExtentionBaseCRUDDao<T>, ISQLExtentionProxyBaseCRUDDao<T>
{
	protected SQLExtentionHolderV3 holder;
	
	public void setHolder(SQLExtentionHolderV3 holder)
	{
		this.holder=holder;
	}
	
	
	

	@Override
	public int insertBatchSelective(String tableName, List<T> records)
	{
		int validCount = 0;
		DBInfo<T>[] dbs = (DBInfo<T>[]) holder.getAllDbinfos(tableName);
		int dbsLength = dbs.length;
		List<List<T>> dbLists = new ArrayList<>(dbs.length);
		// init 
		//这个init操作可以放在下面通过if代替
		for (int i = 0; i < dbs.length; i++)
		{
			dbLists.add(new ArrayList<>());
		}
		for (T t : records)
		{
			int index = (int) (t.getUniquekey().longValue() % dbsLength);
			dbLists.get(index).add(t);
		}
		for (int i = 0; i < dbsLength; i++)
		{
			TableInfo<T>[] tables = dbs[i].getTables();
			int tableCounts = tables.length;
			List<T> dbRecordList = dbLists.get(i);
			List<List<T>> tableLists = new ArrayList<>();
			//table init
			for (int j = 0; j < tableCounts; j++)
			{
				tableLists.add(new ArrayList<>());
			}
			for (T t : dbRecordList)
			{
				int index2 = (int) (t.getUniquekey().longValue() % tableCounts);
				tableLists.get(index2).add(t);
			}
			for (int j = 0; j < tableCounts; j++)
			{
				TableInfo<T> tableInfo = tables[j];
				if(null!=tableLists.get(j)&&!tableLists.get(j).isEmpty())
				{
					List<T> list = tableLists.get(j);
					validCount += dbs[i].getDao().insertBatchSelective(tableInfo.getTableName(), tableLists.get(j));
				}
			}
		}
		return validCount;
	}





}
