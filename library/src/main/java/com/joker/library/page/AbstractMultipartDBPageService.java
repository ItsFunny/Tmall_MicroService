/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午8:27:41
* 
*/
package com.joker.library.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;
import com.joker.library.utils.PageResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午8:27:41
 */
@Slf4j
public abstract class AbstractMultipartDBPageService<T extends AbstractSQLExtentionModel, E extends PageExample>
		implements PageBaseService<List<T>>
{
	@Autowired
	private SQLExtentionHolderV3 holder;

	protected abstract Long countByCondition(DBInfo<T>[] dbs, E example);

	@SuppressWarnings("unchecked")
	@Override
	public PageResponseDTO<List<T>> findByPage(PageRequestDTO pageRequestDTO)
	{
		Map<String, Object> data = pageRequestDTO.getData();
		String tablePrefixName = pageRequestDTO.getTablePrefixName();
		if (StringUtils.isEmpty(tablePrefixName))
		{
			throw new RuntimeException("参数错误,表名不可为空");
		}
		List<? extends ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel>> daos = getAllDaos(
				tablePrefixName);
		DBInfo<T>[] dbs = (DBInfo<T>[]) holder.getAllDbinfos(tablePrefixName);
		E example =  getExample(data);
		Long count = countByCondition(dbs, example);
		if (count <= 0)
		{
			return PageResultUtil.emptyPage();
		}

		if (pageRequestDTO.isSingal())
		{
			log.info("[findByPage]单体查询,查询主键为:{}的{}", pageRequestDTO.getSingleKey(),tablePrefixName);
			return PageResultUtil
					.singleRecordPage(findSingleByPrimaryKey(tablePrefixName, (Number) pageRequestDTO.getSingleKey()));
		}
		long startTime = System.currentTimeMillis();
		int pageSize = pageRequestDTO.getPageSize();
		int pageNum = pageRequestDTO.getPageNum();
		int start = (pageRequestDTO.getPageNum() - 1) * pageRequestDTO.getPageSize();

		int totalTableCounts = holder.getTotalTableCounts(tablePrefixName);
		int avgStart = start / totalTableCounts;

		List<List<T>> totalList = new ArrayList<>();
		// 又多了一条检测的条件:dbs的顺序必须对应daos的顺序
		// 1.查找数据
		// 填充数据
		for (int i = 0; i < daos.size(); i++)
		{
			// 每个表都需要查询数据,然后放入
			List<List<T>> res = findByExample((DBInfo<T>) dbs[i], daos.get(i), example, avgStart,
					pageRequestDTO.getPageSize());
			totalList.addAll(res);
		}
		long firstSearchTime = System.currentTimeMillis();
		log.info("[findByPage],分页查询{},第一次查询结束,耗时:{} ms",tablePrefixName, firstSearchTime - startTime);
		if (totalList.isEmpty())
		{
			return PageResultUtil.emptyPage();
		}
		// 2.获取最小的id
		// 获取最小的那个id
		long minId = 0L;
		if (null == totalList || totalList.isEmpty())
		{
			minId = 0L;
		} else
		{
			minId = getMinId(totalList);
		}
		// 3.获取各个表最大的id
		// 获取各个表返回的最大的那个记录,这里的话有个注意点,就是一个库下可能有多个表,因而还需要记录多个表
		List<Long> maxIdList = new ArrayList<>(totalList.size());
		// 初始化最大值
		// for (int i = 0; i < totalList.size(); i++)
		// {
		// maxIdList.add(Long.MAX_VALUE);
		// }
		// 动态设置最大值
		getMaxId(maxIdList, totalList);
		// 4.二次查询数据,改为id between查询,因为使用的是泛型,无法在这里设置(这里设置也是可以的,不过就需要大更改了)
		// 返回的数据还是一个嵌套的list
		// 必须是按顺序返回
		List<List<T>> secondFindList = new ArrayList<>();
		int recordIndex = 0;
		for (int i = 0; i < daos.size(); i++)
		{
			TableInfo<?>[] tableInfos = dbs[i].getTables();
			// 总共有几张表,则需要查询几次
			for (int j = 0; j < tableInfos.length; j++, recordIndex++)
			{
				// 获取这个表对应的记录,存在记录为空的可能
				// if (totalList.get(recordIndex) == null ||
				// totalList.get(recordIndex).isEmpty())
				// {
				// secondFindList.add(totalList.get(i));
				// continue;
				// }
				// int maxIndex = i * j + i + j;
				String tableConcreteName = tableInfos[j].getTableName();

				long max = maxIdList.get(recordIndex);
				// ((PageExample)example).setEnd(null);
				// ((PageExample)example).setStart(null);
				List<T> sec1 = secondFindByBetween(tableConcreteName, (ISQLExtentionBaseCRUDDao<T>) daos.get(i), minId,
						max, data);
				secondFindList.add(sec1);
			}
		}
		log.info("[findByPage]分页查询{},第二次查询结束,耗时:{}", tablePrefixName,System.currentTimeMillis() - firstSearchTime);
		// secondFindList 获得的长度与第一次是相同的
		int offSite = 0;
		// 5.找到minId所在的偏移量
		List<T> dbRecordsList = new ArrayList<>();
		for (int i = 0; i < secondFindList.size(); i++)
		{
			offSite += (avgStart - (secondFindList.get(i).size() - totalList.get(i).size()));
			dbRecordsList.addAll(secondFindList.get(i));
		}
		// 6.得到全局视野下的偏移量之后,对结果进行排序,然后找到minId所在的下表(这里的排序是要通过id升序的,不需要特殊)
		int beginIndex = start - offSite;// 获取从哪里开始取值
		sortList(dbRecordsList);
		List<T> resList = new ArrayList<>();
		for (int i = beginIndex, j = 0; j < pageSize && i < dbRecordsList.size(); i++, j++)
		{
			resList.add(dbRecordsList.get(i));
		}
		PageResponseDTO<List<T>> pageResponseDTO = new PageResponseDTO<List<T>>(resList, pageSize, pageNum, count);
		return pageResponseDTO;
	}

	private void sortList(List<T> list)
	{
		Collections.sort(list, new Comparator<T>()
		{

			@Override
			public int compare(T o1, T o2)
			{
				return o1.getUniquekey().longValue() <= o2.getUniquekey().longValue() ? -1 : 1;
			}
		});
	}

	protected List<T> findSingleByPrimaryKey(String tableName, Number id)
	{
		SQLExtentionDaoWrapper<AbstractSQLExtentionModel> wrapper = holder.getBaseDao(tableName, id);
		T t = (T) wrapper.getDao().selectByPrimaryKey(wrapper.getTableName(), id);
		return Arrays.asList(t);
	}
	// {
	// SQLExtentionDaoWrapper<AbstractSQLExtentionModel> wrapper =
	// holder.getBaseDao(tableName, id);
	// T t = (T) wrapper.getDao().selectByPrimaryKey(wrapper.getTableName(), id);
	// if (null == t)
	// {
	// return null;
	// } else
	// {
	// return t;
	// }
	// }

	// 可以按照id升序或者其他方式,并且返回的是每个表中的记录
	private List<List<T>> findByExample(DBInfo<T> info,
			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao, Object exampleObj, Integer avgStart,
			Integer pageSize)
	{
		List<List<T>> res = new ArrayList<>();
		TableInfo<T>[] tbs = info.getTables();
		for (TableInfo<T> tableInfo : tbs)
		{
			String tableName = tableInfo.getTableName();
			List<T> t = doFindByExample(tableName, dao, avgStart, pageSize, exampleObj);
			res.add(t);
		}
		return res;
	}

	// 组装example
	protected abstract E getExample(Map<String, Object> condition);

	protected abstract List<T> doFindByExample(String tableName,
			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao, Integer avgStart, Integer end,
			Object exampleObj);

	// 获取最小的id值
	protected abstract Long getMinId(List<List<T>> list);

	// 设置各个表的最大值
	protected abstract void getMaxId(List<Long> maxId, List<List<T>> totalList);

	// 二次查询,重新获取数据,,经过测试发现传入object,not useful,所以还是传入map然后自己构造
	protected abstract List<T> secondFindByBetween(String concreteTableName, ISQLExtentionBaseCRUDDao<T> dao, long min,
			long max, Map<String, Object> condition);

	// 获取各个
	protected List<? extends ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel>> getAllDaos(String tableName)
	{
		return this.holder.getAllDaos(tableName);
	}

}
