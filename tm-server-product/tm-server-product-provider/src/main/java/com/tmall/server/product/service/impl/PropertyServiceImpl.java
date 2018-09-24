/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:33
* 
*/
package com.tmall.server.product.service.impl;

import static org.mockito.Mockito.validateMockitoUsage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.bm.Rule.RPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.AbstractMultipartDBPageService;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
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
import com.tmall.server.product.common.model.TmallPropertyValueExample;
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

	@Lazy
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
		// List<? extends ISQLExtentionBaseCRUDDao<TmallPropertyValue>> daos = holder
		// .getAllDaos(SQLExtentionConstant.PROPERTY_VALUE);
		ISQLExtentionProxyBaseCRUDDao<TmallPropertyValue> proxyDao = holder.getProxyDao(SQLExtentionConstant.PROPERTY_VALUE);
	
		DBInfo<TmallPropertyValue>[] dbs = (DBInfo<TmallPropertyValue>[]) holder
				.getAllDbinfos(SQLExtentionConstant.PROPERTY_VALUE);

		List<List<TmallPropertyValue>> list = new ArrayList<>(dbs.length);
		for (int i = 0; i < dbs.length; i++)
		{
			list.add(new ArrayList<>());
		}
		values.forEach(v -> {
			TmallPropertyValue value = new TmallPropertyValue();
			value.from(v);
			propertyValues.add(value);
			int index = (int) (value.getUniquekey().longValue() % dbs.length);
			list.get(index).add(value);
		});
		int validCount = proxyDao.insertBatchSelective(SQLExtentionConstant.PROPERTY_VALUE, propertyValues);
		if(validCount-propertyValues.size()!=0)
		{
			 log.error("[addPropertyAndValue]本地插入数据失败");
			 throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
		}
		/*
		 * 还需要对其内部进行拆分,尽管将相同数据库下的记录都放在了一起, 但是还有不同表的区别,相同表的放一起
		 */
		// 对内部的数据进行拆分:
//		for (int i = 0; i < list.size(); i++)
//		{
//			Integer tableCounts = dbs[i].getTableCounts();
//			// 有几个表就需要几个list存放
//			List<List<TmallPropertyValue>> tList = new ArrayList<>(tableCounts);
//			for (int k = 0; k < tableCounts; k++)
//			{
//				// 初始化
//				tList.add(new ArrayList<>());
//			}
//			// 拆分数据
//			for (TmallPropertyValue value : list.get(i))
//			{
//				int index2 = (int) (value.getUniquekey().longValue() % tableCounts);
//				TableInfo<?> tableInfo = dbs[i].getTables()[index2];
//				String tableName = tableInfo.getTableName();
//				value.setTableName(tableName);
//				tList.get(index2).add(value);
//			}
//			// 插入操作,内部中的每个list都是相同的表名
//			TableInfo<TmallPropertyValue>[] tableInfos = (TableInfo<TmallPropertyValue>[]) dbs[i].getTables();
//			for (int j = 0; j < tableInfos.length; j++)
//			{
//				List<TmallPropertyValue> l = tList.get(j);
//				if (null == l || l.isEmpty())
//				{
//					continue;
//				}
//				dbs[i].getDao().insertBatchSelective(tableInfos[j].getTableName(), l);
//			}
//			// for (List<TmallPropertyValue> list2 : tList)
//			// {
//			// if (null == list2 || list2.isEmpty())
//			// {
//			// continue;
//			// }
//			// int validCot =
//			// daos.get(i).insertBatchSelective(list2.iterator().next().getTableName(),
//			// list2);
//			// if (validCot < 1)
//			// {
//			// log.error("[addPropertyAndValue]本地插入数据失败");
//			// throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
//			// }
//			// }
//		}
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
		TmallPropertyExample example = new TmallPropertyExample();
		example.setTableName(concreteTableName);
		Criteria criteria = example.createCriteria();
		criteria.andPropertyIdBetween((int) min, (int) max);

		return dao.selectByExample(example);
	}

	@Override
	public ResultDTO<PropertyDTO> showPropertyValues(Integer propertyId)
	{
		SQLExtentionDaoWrapper<TmallProperty> wrapper = holder.getBaseDao(SQLExtentionConstant.PROPERTY, propertyId);
		TmallProperty property = wrapper.getDao().selectByPrimaryKey(wrapper.getTableName(), propertyId);
		if (null == property)
		{
			return ResultUtils.fail("property info not exists ,check and try again");
		}
		TmallPropertyValueExample valueExample = new TmallPropertyValueExample();
		com.tmall.server.product.common.model.TmallPropertyValueExample.Criteria criteria = valueExample
				.createCriteria();
		criteria.andPropertyIdEqualTo(propertyId);
		List<TmallPropertyValue> values = new ArrayList<>();
		DBInfo<TmallPropertyValue>[] dbs = (DBInfo<TmallPropertyValue>[]) holder
				.getAllDbinfos(SQLExtentionConstant.PROPERTY_VALUE);
		for (DBInfo<TmallPropertyValue> dbInfo : dbs)
		{
			ISQLExtentionBaseCRUDDao<TmallPropertyValue> dao = dbInfo.getDao();
			TableInfo<TmallPropertyValue>[] tables = dbInfo.getTables();
			for (TableInfo<TmallPropertyValue> tableInfo : tables)
			{
				valueExample.setTableName(tableInfo.getTableName());
				List<TmallPropertyValue> vals = dao.selectByExample(valueExample);
				if (null != vals && !vals.isEmpty())
				{
					values.addAll(vals);
				}
			}
		}
		PropertyDTO dto = new PropertyDTO();
		property.to(dto);
		if (!values.isEmpty())
		{
			List<PropertyValueDTO> vdtos = new ArrayList<>();
			values.forEach(v -> {
				PropertyValueDTO valueDTO = new PropertyValueDTO();
				v.to(valueDTO);
				vdtos.add(valueDTO);
			});
			dto.setValues(vdtos);
		}
		return ResultUtils.sucess(dto);
	}

	@RabbitMQTransaction
	@Override
	public ResultDTO<?> updateProperty(UserRecordAspectWrapper<PropertyDTO> wrapper,List<Long>deleteIds)
	{
		log.info("[updateProperty]更新property,wrapper:{}", wrapper);
		PropertyDTO propertyDTO = wrapper.getData();
		Integer propertyId = propertyDTO.getPropertyId();
		if (propertyId == null || propertyId < 0)
		{
			return ResultUtils.fail("不合法的参数");
		}
		SQLExtentionDaoWrapper<TmallProperty> sqlWrapper = holder.getBaseDao(SQLExtentionConstant.PROPERTY,
				propertyDTO.getPropertyId());
		TmallProperty property = new TmallProperty();
		property.from(propertyDTO);
		// 这里直接插入即可,内部修改了sql语句,只不过名字还没改
		property.setTableName(sqlWrapper.getTableName());
		Integer integer = sqlWrapper.getDao().insertSelective(property);
		if (integer < 1)
		{
			log.info("[updateProperty]更新本地属性,无法插入数据:{}", property);
			throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
		}
		//删除过期的
		if(null!=deleteIds&&!deleteIds.isEmpty())
		{
			DBInfo<TmallPropertyValue>[] allDbinfos = (DBInfo<TmallPropertyValue>[]) holder.getAllDbinfos(SQLExtentionConstant.PROPERTY_VALUE);
			TmallPropertyValueExample example=new TmallPropertyValueExample();
			com.tmall.server.product.common.model.TmallPropertyValueExample.Criteria criteria = example.createCriteria();
			criteria.andPropertyValueIdIn(deleteIds);
			Integer validDelCount=0;
			for (DBInfo<TmallPropertyValue> dbInfo : allDbinfos)
			{
				TableInfo<TmallPropertyValue>[] tableInfos = dbInfo.getTables();
				ISQLExtentionBaseCRUDDao<TmallPropertyValue> dao = dbInfo.getDao();
				for (TableInfo<TmallPropertyValue> tableInfo : tableInfos)
				{
					example.setTableName(tableInfo.getTableName());
					validDelCount+=dao.deleteByExample(example);
				}
			}
			if(validDelCount-deleteIds.size()!=0)
			{
				log.error("[updateProperty]删除本地属性value值的时候出错,预期删除{}条,实际删除{}",deleteIds.size(),validDelCount);
				throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
			}
		}
		// 插入或者更新value值
		ISQLExtentionProxyBaseCRUDDao<TmallPropertyValue> proxyDao = holder
				.getProxyDao(SQLExtentionConstant.PROPERTY_VALUE);
		List<PropertyValueDTO> valueDTOs = propertyDTO.getValues();
		if (null != valueDTOs && !valueDTOs.isEmpty())
		{
			List<TmallPropertyValue> values = new ArrayList<>();
			valueDTOs.forEach(v -> {
				TmallPropertyValue value = new TmallPropertyValue();
				value.from(v);
				values.add(value);
			});
			int validCount = proxyDao.insertBatchSelective(SQLExtentionConstant.PROPERTY_VALUE, values);
			if (validCount - values.size() != 0)
			{
				log.error("[updateProperty]本地插入属性值,实际插入的与预期的不一致,预期插入:{}条,实际:{}", values.size(), validCount);
				throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
			}
		}
		return ResultUtils.sucess();
	}

}
