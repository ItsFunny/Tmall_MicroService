/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:33
* 
*/
package com.tmall.server.product.service.impl;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.AbstractPageService;
import com.joker.library.page.PageBaseService;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.service.IdWorkerService;
import com.joker.library.service.IdWorkerServiceTwitter;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.annotation.ArgumentCheckAnnotation;
import com.tmall.common.annotation.RabbitMQTransaction;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.other.ISQLExtentionBaseCRUDService;
import com.tmall.common.other.SQLExtentionHolder;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.common.model.TmallPropertyExample;
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
public class PropertyServiceImpl extends AbstractPageService<List<PropertyDTO>>implements IPropertyService
{

	@Autowired
	private SQLExtentionHolderV3 holder;

//	public static void main(String[] args)
//	{
//		IdWorkerServiceTwitter idWorkerServiceTwitter = new IdWorkerServiceTwitter(1l, 0L);
//		for (int i = 0; i < 10; i++)
//		{
//			Thread thread = new Thread(new Runnable()
//			{
//
//				@Override
//				public void run()
//				{
//					for (int i = 0; i < 10000; i++)
//					{
//						long nextId = idWorkerServiceTwitter.nextId();
//						if (nextId < 0)
//						{
//							System.out.println(nextId);
//						}
//					}
//				}
//			});
//			thread.start();
//		}
//	}

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
				if(null==list2||list2.isEmpty())
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
		Map<String, Object> data = pageRequestDTO.getData();
		return null;
	}

	@Override
	protected List<PropertyDTO> findSingleById(Object id)
	{
		SQLExtentionDaoWrapper<TmallProperty> wrapper = holder.getBaseDao(SQLExtentionConstant.PROPERTY, (Number) id);
		TmallProperty tmallProperty = wrapper.getDao().selectByPrimaryKey(wrapper.getTableName(),(Number) id);
		PropertyDTO propertyDTO=new PropertyDTO();
		tmallProperty.to(propertyDTO);
		return Arrays.asList(propertyDTO);
	}

	@Override
	public List<PropertyDTO> findByCondition(Integer start, Integer end, Map<String, Object> condition)
	{
		TmallPropertyExample example=new TmallPropertyExample();
		example.setStart(start);
		example.setEnd(end);
		List<? extends ISQLExtentionBaseCRUDDao<AbstractSQLExtentionModel>> daos = holder.getAllDaos(SQLExtentionConstant.PROPERTY);
		
		return null;
	}

	@Override
	public List<PropertyDTO> findAllByPage(Integer start, Integer end)
	{
		return null;
	}

	@Override
	public Long countByCondition(Map<String, Object> condition)
	{
		// TODO Auto-generated method stub
		return null;
	}


}
