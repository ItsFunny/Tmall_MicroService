///**
//*
//* @author joker 
//* @date 创建时间：2018年9月15日 下午3:55:44
//* 
//*/
//package com.tmall.server.product.dao.sqlextention;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.tmall.common.other.ISQLExtentionCRUDDao;
//import com.tmall.server.product.common.model.TmallCategory;
//import com.tmall.server.product.common.model.TmallCategoryExample;
//import com.tmall.server.product.dao.db1.Db1CategoryDao;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年9月15日 下午3:55:44
//*/
//@Repository(value="db1DaoImpl")
//public class DB1CategorySQLExtentionDaoImpl  implements ISQLExtentionCRUDDao<TmallCategory>
//{
//	@Autowired
//	private Db1CategoryDao db1CategoryDao;
//
//	@Override
//	public Integer insertSelective(String tableName, TmallCategory t)
//	{
//		TmallCategory category=(TmallCategory) t;
//		category.setTableName(tableName);
//		return db1CategoryDao.insertSelective(category);
//	}
//
//	@Override
//	public TmallCategory findByPrimaryKey(Number id)
//	{
//		// TODO Auto-generated method stub
//		return db1CategoryDao.selectByPrimaryKey(id.intValue());
//	}
//	@Override
//	public Integer updateSelectTive(String tableName, TmallCategory t,Object example)
//	{
//		return db1CategoryDao.updateByExampleSelective((TmallCategory) t, (TmallCategoryExample)example);
//	}
//
//	@Override
//	public List<TmallCategory> findByExample(Object example)
//	{
//		TmallCategoryExample example2=(TmallCategoryExample) example;
//		return db1CategoryDao.selectByExample(example2);
//	}
//
//	@Override
//	public Integer deleteInBatchByExample(Object example)
//	{
//		TmallCategoryExample example2=(TmallCategoryExample) example;
//		return db1CategoryDao.deleteByExample(example2);
//	}
//
////	@Override
////	public List<TmallCategory> selectByExample(TmallCategoryExample e)
////	{
////		return db1CategoryDao.selectByExample(e);
////	}
//
//}
