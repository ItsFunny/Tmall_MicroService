/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午1:38:11
* 
*/
package com.tmall.common.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.tmall.common.db.ExtentionInfo;
import com.tmall.common.db.ExtentionInfo.DBInfo;
import com.tmall.common.db.ExtentionInfo.TableInfo;

import lombok.extern.slf4j.Slf4j;

import com.tmall.common.db.ExtentionResult;

/**
 * 配置信息的格式为:表名:分库个数:分别对应的分表个数=分别对应的数据源dao类,用逗号分隔 如
 * category:2:1=com.test.DB0CategoryDao,2=com.test.DB1CategoryDao
 * 注意数据库中表的名字必须明确为category_1等这样的格式,并且必须按升序的方式配置,如
 * 在数据库中是db0,db1 则dao配置类必须是db0Dao,再db1Dao,不可颠倒(颠倒一次就崩了),单个查询的时候会出错
 * 
 * @author joker
 * @date 创建时间：2018年9月15日 下午1:38:11
 */
@Slf4j
@SuppressWarnings("unchecked")
public class SQLExtentionHolder
{
	// 保存着表名对应的扩展配置信息
	private Map<String, ExtentionInfo> sqlRepository;
	
	public SQLExtentionHolder()
	{
		this.sqlRepository=new HashMap<String, ExtentionInfo>();
	}
	

	public void config(String configString,ApplicationContext context)
	{
		// 总共有几个表需要配置
		String[] strings = configString.split("-");
		for (int i=0;i<strings.length;i++)
		{
			ExtentionInfo info=new ExtentionInfo();
		
			// 详细信息,如表名,数据库个数
			String[] details = strings[i].split(":");
			String tableName = details[0];
			Integer dbCounts = Integer.parseInt(details[1]);
			info.setDbCounts(dbCounts);
			DBInfo[] dbs=new DBInfo[dbCounts];
			// 每个表的详细信息,如有几张表,每个数据库对应的dao
			String[] strings2 = details[2].split(",");
			for (int j=0;j<strings2.length;j++)
			{
				dbs[j]=new DBInfo();
				String[] strings3 = strings2[j].split("=");
				Integer tableCountss = Integer.parseInt(strings3[0]);
				dbs[j].setTableCounts(tableCountss);
				String configClassName = strings3[1];
				Object bean = context.getBean(configClassName);
				if(!(bean instanceof ISQLExtentionCRUDDao))
				{
					throw new RuntimeException("sql扩展配置错误,对应的dao类不是指定的类型");
				}
				dbs[j].setBaseDao((ISQLExtentionCRUDDao) bean);
			}
			info.setDbs(dbs);
			sqlRepository.put(tableName, info);
		}
	}

	public static void main(String[] args)
	{
		String configString = "category:2:1=test,2=test2-user:1:3=test3";

		String[] strings = configString.split("-");
		for (String string : strings)
		{
			// 详细信息,如表名,数据库个数
			String[] details = string.split(":");
			String tableName = details[0];
			Integer dbCounts = Integer.parseInt(details[1]);
			// 每个表的详细信息,如果有几张表,每个数据库对应的dao
			String[] strings2 = details[2].split(",");
			for (String string2 : strings2)
			{
				String[] strings3 = string2.split("=");
				Integer tableCountss = Integer.parseInt(strings3[0]);
				String configClassName = strings3[1];
				System.out
						.println("有" + dbCounts + "个数据库配置" + ",分别有:" + tableCountss + "张表,对应的配置类为:" + configClassName);
			}
		}
	}

	public<T> SQLExtentionDAOWrapper<T> getBaseDao(String tableName, Long hashCodeOrId)
	{
		ExtentionInfo extentionInfo = sqlRepository.get(tableName);
		if (null == extentionInfo)
		{
			throw new RuntimeException("表名:" + tableName + "对应的配置信息不存在");
		}
		Integer dbIndex = (int) (hashCodeOrId % extentionInfo.getDbCounts());
		DBInfo db = extentionInfo.getDbs()[dbIndex];
		if (null == db)
		{
			log.error("[]下标{},对应的db信息不存在,已配置的db:{}", dbIndex, extentionInfo.getDbs());
			throw new RuntimeException("配置错误,下标对应的配置信息不存在");
		}

		Integer tableIndex = (int) (hashCodeOrId % db.getTableCounts());
		tableName += "_" + tableIndex;// 至于命名规则也可以保存在配置中然后取
		SQLExtentionDAOWrapper<T> wrapper = new SQLExtentionDAOWrapper<>();
		wrapper.setTableDetailName(tableName);
		wrapper.setBaseDao(db.getBaseDao());
		return wrapper;
	}

	public List<ISQLExtentionCRUDDao>getTableAllDaos(String tableName)
	{
		ExtentionInfo extentionInfo = sqlRepository.get(tableName);
		if(null==extentionInfo)
		{
			log.error("在sql扩展配置中,{}对应的配置为空",tableName);
			throw new RuntimeException(tableName+"对应的配置不存在");
		}
		DBInfo[] dbs = extentionInfo.getDbs();
		if(null==dbs)
		{
			throw new RuntimeException(tableName+"对应的数据库信息为空,请确认配置信息是否正确");
		}
		List<ISQLExtentionCRUDDao>daos=new ArrayList<ISQLExtentionCRUDDao>();
		for (DBInfo dbInfo : dbs)
		{
			daos.add((ISQLExtentionCRUDDao) dbInfo.getBaseDao());
		}
		return daos;
	}
}
