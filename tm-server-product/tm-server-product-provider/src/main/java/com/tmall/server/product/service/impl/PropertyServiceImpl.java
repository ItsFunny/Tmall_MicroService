/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:33
* 
*/
package com.tmall.server.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.AbstractMultipartDBPageService;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.annotation.RabbitMQTransaction;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.common.model.TmallPropertyExample;
import com.tmall.server.product.common.model.TmallPropertyExample.Criteria;
import com.tmall.server.product.common.model.TmallPropertyValue;
import com.tmall.server.product.exception.TmallProductException;
import com.tmall.server.product.service.IPropertyService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 上午10:33:33
 */
@Service
@Slf4j
public class PropertyServiceImpl extends AbstractMultipartDBPageService<TmallProperty, TmallPropertyExample>
		implements IPropertyService
{

	@Autowired
	private SQLExtentionHolderV3 holder;

	// public static void main(String[] args)
	// {
	// IdWorkerServiceTwitter idWorkerServiceTwitter = new
	// IdWorkerServiceTwitter(1l, 0L);
	// for (int i = 0; i < 10; i++)
	// {
	// Thread thread = new Thread(new Runnable()
	// {
	//
	// @Override
	// public void run()
	// {
	// for (int i = 0; i < 10000; i++)
	// {
	// long nextId = idWorkerServiceTwitter.nextId();
	// if (nextId < 0)
	// {
	// System.out.println(nextId);
	// }
	// }
	// }
	// });
	// thread.start();
	// }
	// }

	@RabbitMQTransaction
	@Override
	public ResultDTO<?> addPropertyAndValue(UserRecordAspectWrapper<PropertyDTO> wrapper)
	{
		PropertyDTO propertyDTO = wrapper.getData();
		Integer propertyId = propertyDTO.getPropertyId();
		SQLExtentionDaoWrapper<TmallProperty> propSqlWrapper = holder.getBaseDao(SQLExtentionConstant.PROPERTY,
				propertyId);
		TmallProperty property = new TmallProperty();
		property.from(propertyDTO);
		property.setTableName(propSqlWrapper.getTableName());
		int propValidCount = propSqlWrapper.getDao().insert(property);
		if (propValidCount < 1)
		{
			log.error("[addPropertyAndValue]在本地插入property的时候失败");
			throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
		}
		// 插入value
		List<PropertyValueDTO> values = propertyDTO.getValues();
		List<TmallPropertyValue> propertyValues = new ArrayList<TmallPropertyValue>();
		List<? extends ISQLExtentionBaseCRUDDao<TmallPropertyValue>> daos = holder
				.getAllDaos(SQLExtentionConstant.PROPERTY_VALUE);
		DBInfo<?>[] dbs = holder.getAllDbinfos(SQLExtentionConstant.PROPERTY_VALUE);

		List<List<TmallPropertyValue>> list = new ArrayList<>(daos.size());
		for (int i = 0; i < daos.size(); i++)
		{
			list.add(new ArrayList<>());
		}
		values.forEach(v -> {
			TmallPropertyValue value = new TmallPropertyValue();
			value.from(v);
			propertyValues.add(value);
			int index = (int) (value.getUniquekey().longValue() % daos.size());
			list.get(index).add(value);
		});
		/*
		 * 还需要对其内部进行拆分,尽管将相同数据库下的记录都放在了一起, 但是还有不同表的区别,相同表的放一起
		 */
		// 对内部的数据进行拆分:
		for (int i = 0; i < list.size(); i++)
		{
			Integer tableCounts = dbs[i].getTableCounts();
			// 有几个表就需要几个list存放
			List<List<TmallPropertyValue>> tList = new ArrayList<>(tableCounts);
			for (int k = 0; k < tableCounts; k++)
			{
				// 初始化
				tList.add(new ArrayList<>());
			}
			// 拆分数据
			for (TmallPropertyValue value : list.get(i))
			{
				int index2 = (int) (value.getUniquekey().longValue() % tableCounts);
				TableInfo<?> tableInfo = dbs[i].getTables()[index2];
				String tableName = tableInfo.getTableName();
				value.setTableName(tableName);
				tList.get(index2).add(value);
			}
			// 插入操作,内部中的每个list都是相同的表名
			for (List<TmallPropertyValue> list2 : tList)
			{
				if (null == list2 || list2.isEmpty())
				{
					continue;
				}
				int validCot = daos.get(i).insertBatchSelective(list2.iterator().next().getTableName(), list2);
				if (validCot < 1)
				{
					log.error("[addPropertyAndValue]本地插入数据失败");
					throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
				}
			}
		}
		return ResultUtils.sucess();
	}

	@Override
	public PageResponseDTO<List<PropertyDTO>> findByCondition(PageRequestDTO pageRequestDTO)
	{
		PageResponseDTO<List<TmallProperty>> responseDTO = super.findByPage(pageRequestDTO);
		List<TmallProperty> data = responseDTO.getData();
		List<PropertyDTO> dtos = new ArrayList<>();
		if (null != data && !data.isEmpty())
		{
			data.forEach(d -> {
				PropertyDTO propertyDTO = new PropertyDTO();
				d.to(propertyDTO);
				dtos.add(propertyDTO);
			});
		}
		PageResponseDTO<List<PropertyDTO>> pageResponseDTO = new PageResponseDTO<List<PropertyDTO>>(dtos,
				responseDTO.getPageSize(), responseDTO.getPageNum(), responseDTO.getTotalCount());
		return pageResponseDTO;
	}

	@Override
	protected Long countByCondition(DBInfo<TmallProperty>[] dbs, TmallPropertyExample example)
	{
		// example 很可能不起作用
		Long totalCount = 0L;
		for (DBInfo<TmallProperty> dbInfo : dbs)
		{
			ISQLExtentionBaseCRUDDao<TmallProperty> dao = dbInfo.getDao();
			TableInfo<TmallProperty>[] tables = dbInfo.getTables();
			for (TableInfo<TmallProperty> tableInfo : tables)
			{
				example.setTableName(tableInfo.getTableName());
				totalCount += dao.countByExample(example);
			}
		}
		return totalCount;
	}

	@Override
	protected TmallPropertyExample getExample(Map<String, Object> condition)
	{
		TmallPropertyExample example = new TmallPropertyExample();
		return example;
	}

	@Override
	protected List<TmallProperty> doFindByExample(String tableName,
			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao, Integer avgStart, Integer end,
			Object exampleObj)
	{
		TmallPropertyExample propertyExample = (TmallPropertyExample) exampleObj;
		propertyExample.setStart(avgStart);
		propertyExample.setEnd(end);
		propertyExample.setTableName(tableName);
		return (List<TmallProperty>) dao.selectByExample(propertyExample);
	}

	@Override
	protected Long getMinId(List<List<TmallProperty>> list)
	{
		long minId = Long.MAX_VALUE;
		for (List<TmallProperty> list2 : list)
		{
			if (list2.isEmpty())
			{
				continue;
			}
			Long cid = ((Number) list2.iterator().next().getPropertyId()).longValue();
			minId = minId < cid ? minId : cid;
		}
		return minId;
	}

	@Override
	protected void getMaxId(List<Long> maxId, List<List<TmallProperty>> totalList)
	{
		// TODO Auto-generated method stub
		for (List<TmallProperty> list : totalList)
		{
			if (list == null || list.isEmpty())
			{
				maxId.add((long) Integer.MAX_VALUE);
			} else
			{
				Integer m = list.get(list.size() - 1).getPropertyId();
				maxId.add(((Number) m).longValue());
			}
		}

	}

	@Override
	protected List<TmallProperty> secondFindByBetween(String concreteTableName,
			ISQLExtentionBaseCRUDDao<TmallProperty> dao, long min, long max, Map<String, Object> condition)
	{
		TmallPropertyExample example=new TmallPropertyExample();
		example.setTableName(concreteTableName);
		Criteria criteria = example.createCriteria();
		criteria.andPropertyIdBetween((int)min, (int)max);
		
		return dao.selectByExample(example);
	}

}
