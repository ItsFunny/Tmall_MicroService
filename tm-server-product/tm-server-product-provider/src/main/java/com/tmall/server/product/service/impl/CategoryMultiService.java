///**
//*
//* @Description
//* @author joker 
//* @date 创建时间：2018年9月23日 上午8:06:11
//* 
//*/
//package com.tmall.server.product.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.joker.library.page.AbstractMultipartDBPageService;
//import com.joker.library.sqlextention.AbstractSQLExtentionModel;
//import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
//import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
//import com.joker.library.sqlextention.SQLExtentionHolderV3;
//import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
//import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;
//import com.tmall.common.constants.SQLExtentionConstant;
//import com.tmall.common.dto.CategoryDTO;
//import com.tmall.server.product.common.model.TmallCategory;
//import com.tmall.server.product.common.model.TmallCategoryExample;
//import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
//
///**
// * 
// * @When
// * @Description
// * @Detail
// * @author joker
// * @date 创建时间：2018年9月23日 上午8:06:11
// */
//@Service
//public class CategoryMultiService extends AbstractMultipartDBPageService<TmallCategory>
//{
//	@Autowired
//	private SQLExtentionHolderV3 holder;
//
//	@Override
//	protected List<TmallCategory> doFindByExample(String tableName,
//			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao,Integer avgStart,Integer pageSize,Object exampleObj)
//	{
//		TmallCategoryExample example = (TmallCategoryExample) exampleObj;
//		example.setTableName(tableName);
//		if(null!=avgStart)
//		{
//			example.setStart(avgStart);
//		}
//		if(null!=pageSize)
//		{
//			example.setEnd(pageSize);
//		}
//		List<TmallCategory> categories = (List<TmallCategory>) dao.selectByExample(example);
//		return categories;
//	}
//
//	@Override
//	protected Long getMinId(List<List<TmallCategory>> list)
//	{
//		long minId = Long.MAX_VALUE;
//		for (List<TmallCategory> list2 : list)
//		{
//			if (list2.isEmpty())
//			{
//				continue;
//			}
//			Integer cId = list2.iterator().next().getCategoryId();
//			minId = minId < cId ? minId : cId;
//		}
//		return minId;
//	}
//
//	@Override
//	protected void getMaxId(List<Long> maxIdList, List<List<TmallCategory>> totalList)
//	{
//		for (List<TmallCategory> l : totalList)
//		{
//			// wont empty,,checked before
//			maxIdList.add(((Number) l.get(l.size()-1).getCategoryId()).longValue());
//		}
//	}
//
//	@Override
//	protected List<TmallCategory> secondFindByBetween(String concreteTableName,
//			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao, long min, long max,
//			Map<String, Object> condition)
//	{
//		TmallCategoryExample example = new TmallCategoryExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andCategoryIdBetween((int) min, (int) max);
//		// condition
//		List<TmallCategory> categories = (List<TmallCategory>) dao.selectByExample(example);
//		return categories;
//	}
//
//	@Override
//	protected Long countByCondition(DBInfo<TmallCategory>[] dbs, Object exampleObj)
//	{
//		TmallCategoryExample example=(TmallCategoryExample) exampleObj;
////		Criteria criteria = example.createCriteria();
//		Long count=0L;
//		for (DBInfo<TmallCategory> dbInfo : dbs)
//		{
//			ISQLExtentionBaseCRUDDao<TmallCategory> dao = dbInfo.getDao();
//			TableInfo<TmallCategory>[] tables = dbInfo.getTables();
//			for (TableInfo<TmallCategory> tableInfo : tables)
//			{
//				example.setTableName(tableInfo.getTableName());
//				count+=dao.countByExample(example);
//			}
//		}
//		return count;
//	}
//
//	@Override
//	protected Object getExample(Map<String, Object> condition)
//	{
//		
//		return null;
//	}
//
//
//
//
//}
