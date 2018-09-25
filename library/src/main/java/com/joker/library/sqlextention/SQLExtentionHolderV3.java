/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午3:24:17
* 
*/
package com.joker.library.sqlextention;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月21日 下午3:24:17
 */
@Slf4j
public class SQLExtentionHolderV3
{
	private final Map<String, SQLExtentionInfo<? extends AbstractSQLExtentionModel>> sqlExtentionRepository = new HashMap<String, SQLExtentionInfo<?>>();
	private ApplicationContext context;

	public void config(SQLExtentionConfigProperty property, ApplicationContext context)
	{
		this.context = context;
		Integer totalTableCounts = property.getTotalTableCounts();
		// 2:daoImpl1:1=category_0,category_1;1=category_0,category_1
		String configStr = property.getDetailConfigStr();
		String[] prefixNames = property.getTablePrefixNames().split(",");
		String[] tables = configStr.split("-");
		if (prefixNames.length != tables.length)
		{
			throw new RuntimeException("预期的表的前缀名的数量与实际不符");
		}
		if (tables.length != property.getTotalTableCounts())
		{
			throw new RuntimeException("预期配置表的数量与实际不符");
		}
		for (int i = 0; i < tables.length; i++)
		{
			// 2:daoImpl1:2=category0,category_1;1=category_0
			SQLExtentionInfo<?> info = new SQLExtentionInfo<>();

			info.setTablePrefixName(prefixNames[i]);
			String s1 = tables[i];
			String[] strings = s1.split(":");
			// 2 daoImpl1 2=cateogry0,category_1;1=category_0
			DBInfo<?>[] dbs = new DBInfo[Integer.parseInt(strings[0])];
			Object bean = context.getBean(strings[1]);
			if (!(bean instanceof ISQLExtentionProxyBaseCRUDDao))
			{
				throw new RuntimeException("配置错误,配置的bean类型不是指定的类型,请确认是否继承了ISQLExtentionProxyBaseCRUDDao接口");
			}
			ISQLExtentionProxyBaseCRUDDao<?> proxyDao = (ISQLExtentionProxyBaseCRUDDao<?>) bean;
			List<ISQLExtentionBaseCRUDDao<?>> daos = (List<ISQLExtentionBaseCRUDDao<?>>) proxyDao.getAllDaos();
			if (daos.size() != dbs.length)
			{
				log.info("[config]配置错误,数据库对应的dao类数目错误,错误,一个数据库对应一个dao类已配置了{}个dao类,实际需求的为{}个", daos.size(), dbs.length);
				throw new RuntimeException("配置错误");
			}
			// 不同的模式对应不同的存储类,而AbstractSQLEXtentionProxyBaseCURDDao对应的存储结构就是这个类
			//也给不同的选择,可以选择不继承那个代理抽象类,service调用逻辑自己实现
			if (proxyDao instanceof AbstractSQLExtentionpProxyBaseCRUDDao)
			{
				((AbstractSQLExtentionpProxyBaseCRUDDao<?>) proxyDao).setHolder(this);
			}
			info.setProxyDao(proxyDao); // 循环依赖bug,将他移到后面设置
//			info.setProxyDaoBeanName(strings[1]);
			String[] tableInfos = strings[2].split(";");
			// 2=category_0,category_1
			for (int j = 0; j < tableInfos.length; j++)
			{
				dbs[j] = new DBInfo<>();
				String[] strings2 = tableInfos[j].split("=");
				// 2 category_0,category_1
				dbs[j].setTableCounts(Integer.parseInt(strings2[0]));
				TableInfo<?>[] tabs = new TableInfo[Integer.parseInt(strings2[0])];
				String[] names = strings2[1].split(",");
				// category_0,category_1
				// 这里需要进行判断,表申明个数和提供的表的名字个数是否一致
				for (int k = 0; k < names.length; k++)
				{
					tabs[k] = new TableInfo<>();
					tabs[k].setTableName(names[k]);
				}
				// 这里好像只需要用外面这层循环即可
				dbs[j].setDao((ISQLExtentionBaseCRUDDao<?>) daos.get(j));
				dbs[j].setTables(tabs);
			}
			info.setDbs(dbs);
			this.sqlExtentionRepository.put(prefixNames[i], info);
		}
	}

//	@PostConstruct
//	public void afterPropertiesSet()
//	{
//		Set<Entry<String, SQLExtentionInfo<? extends AbstractSQLExtentionModel>>> entry = this.sqlExtentionRepository
//				.entrySet();
//		for (Entry<String, SQLExtentionInfo<? extends AbstractSQLExtentionModel>> entry2 : entry)
//		{
//			SQLExtentionInfo<? extends AbstractSQLExtentionModel> info = entry2.getValue();
//			String beanName = info.getProxyDaoBeanName();
//			Object bean = context.getBean(beanName);
//			if (!(bean instanceof ISQLExtentionProxyBaseCRUDDao))
//			{
//				throw new RuntimeException("配置错误,配置的bean类型不是指定的类型,请确认是否继承了ISQLExtentionProxyBaseCRUDDao接口");
//			}
//			ISQLExtentionProxyBaseCRUDDao<?> proxyDao = (ISQLExtentionProxyBaseCRUDDao<?>) bean;
//			info.setProxyDao(proxyDao);
//		}
//	}

	/*
	 * 这里有多种做法: 1.通过这种方式 2.可以将dao的信息存放在
	 */
	public <T extends AbstractSQLExtentionModel> SQLExtentionDaoWrapper<T> getBaseDao(String tableName,
			Number uniqueKey)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		ISQLExtentionProxyBaseCRUDDao<?> proxyBaseCRUDDao = info.getProxyDao();
		ISQLExtentionBaseCRUDDao<?> dao = proxyBaseCRUDDao.getDetailConfigDao(uniqueKey);
		DBInfo<?>[] dbs = info.getDbs();
		DBInfo<?> db = dbs[(int) (uniqueKey.longValue() % dbs.length)];
		TableInfo<?> tableInfo = db.getTables()[(int) (uniqueKey.longValue() % db.getTableCounts())];
		String concreteName = tableInfo.getTableName();
		SQLExtentionDaoWrapper<?> wrapper = new SQLExtentionDaoWrapper<>();
		wrapper.setDao(dao);
		wrapper.setTableName(concreteName);
		wrapper.setTableCounts(db.getTableCounts());
		return (SQLExtentionDaoWrapper<T>) wrapper;
	}

	public Integer getDBCounts(String tableName)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		return info.getDbs().length;
	}

	public Integer getTableCounts(String tableName, Number key)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		DBInfo<?> dbinfo = info.getDbs()[(int) (key.longValue() % info.getDbs().length)];
		return dbinfo.getTableCounts();
	}

	public <T extends AbstractSQLExtentionModel> ISQLExtentionProxyBaseCRUDDao<T> getProxyDao(String tableName)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		ISQLExtentionProxyBaseCRUDDao<?> proxyDao = info.getProxyDao();
		return (ISQLExtentionProxyBaseCRUDDao<T>) proxyDao;
	}

	public <T extends AbstractSQLExtentionModel> List<? extends ISQLExtentionBaseCRUDDao<T>> getAllDaos(
			String tableName)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		ISQLExtentionProxyBaseCRUDDao<?> proxyDao = info.getProxyDao();
		return (List<? extends ISQLExtentionBaseCRUDDao<T>>) proxyDao.getAllDaos();

	}

	public DBInfo<? extends AbstractSQLExtentionModel>[] getAllDbinfos(String tableName)
	{
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		return info.getDbs();
	}

	public Integer getTotalTableCounts(String tableName)
	{
		return this.sqlExtentionRepository.get(tableName).getTotalTableCounts();
	}

	public List<TableInfo<?>> getAllTables(String tableName)
	{
		List<TableInfo<?>> res = new ArrayList<SQLExtentionInfo.TableInfo<?>>();
		SQLExtentionInfo<?> info = this.sqlExtentionRepository.get(tableName);
		DBInfo<?>[] dbs = info.getDbs();
		for (DBInfo<?> dbInfo : dbs)
		{
			res.addAll(Arrays.asList(dbInfo.getTables()));
		}
		return res;
	}
}
