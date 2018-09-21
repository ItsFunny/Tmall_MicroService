///**
//*
//* @Description
//* @author joker 
//* @date 创建时间：2018年9月20日 下午9:00:00
//* 
//*/
//package com.tmall.server.product.dao.sqlextention;
//
//import java.lang.annotation.Annotation;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.joker.library.sqlextention.AbstractSQLExtentionBaseCRUDDao;
//import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
//import com.tmall.server.product.common.model.TmallCategory;
//import com.tmall.server.product.dao.TmallCategoryDao;
//
///**
// * 
// * @When
// * @Description
// * @Detail
// * @author joker
// * @date 创建时间：2018年9月20日 下午9:00:00
// */
//@Component(value = "categoryFacadedDaoImpl")
//public class CategoryFacadedDaoImpl extends AbstractSQLExtentionBaseCRUDDao<TmallCategory>
//{
//	// 必须明确确定配置是否正确,不然各种越界异常
//	@Autowired
//	private List<TmallCategoryDao> daos;
//
//	@PostConstruct
//	public void checkConfig()
//	{
//		for (int i = 0; i < daos.size(); i++)
//		{
//			Annotation[] annotations = daos.get(i).getClass().getAnnotations();
//			Order order = daos.get(i).getClass().getAnnotation(Order.class);
//			if (null == order)
//			{
//				throw new RuntimeException("配置有误");
//			}
//			int index = order.value();
//			if (index != i)
//			{
//				throw new RuntimeException("配置有误");
//			}
//		}
//
//	}
//
//	@Override
//	public long countByExample(Object example)
//	{
//		Long res=0L;
//		for (TmallCategoryDao tmallCategoryDao : daos)
//		{
//			res+=tmallCategoryDao.countByExample(example);
//		}
//		return res;
//	}
//
//	@Override
//	public int deleteByExample(Object example)
//	{
//		
//		return 0;
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Number primaryKey)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insert(TmallCategory record)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int insertSelective(TmallCategory record)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<TmallCategory> selectByExample(Object example)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public TmallCategory selectByPrimaryKey(Number primaryKey)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int updateByExampleSelective(TmallCategory record, Object example)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByExample(TmallCategory record, Object example)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(TmallCategory record)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByPrimaryKey(TmallCategory record)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public ISQLExtentionBaseCRUDDao<TmallCategory> getByIndex(int index)
//	{
//		return this.daos.get(index);
//	}
//
//	@Override
//	public List<ISQLExtentionBaseCRUDDao<TmallCategory>> getDetailDaos()
//	{
//		List<ISQLExtentionBaseCRUDDao<TmallCategory>> crudDaos = new ArrayList<>(daos);
//		return crudDaos;
//	}
//
//}
