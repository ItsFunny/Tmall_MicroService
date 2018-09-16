///**
//*
//* @author joker 
//* @date 创建时间：2018年9月16日 上午8:37:25
//* 
//*/
//package com.tmall.common.other;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.joker.library.page.AbstractPageService;
//import com.tmall.common.dto.CategoryDTO;
//import com.tmall.common.dto.PageExample;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 
// * @author joker
// * @param T代表实体类,E代表Example类,和P代表要返回的分页类型
// * @date 创建时间：2018年9月16日 上午8:37:25
// */
//@Slf4j
//public abstract class AbstractSQLExtentionWithPageService<T,E extends PageExample, P> extends AbstractPageService<P>
//		implements ISQLExtentionBaseCRUDService
//{
//	@Autowired
//	private SQLExtentionHolder holder;
//	
//	//不同的实现类有不同的tablePrefixName,并且不会更改
//	protected  String tablePrefixName;
//
//	//循环遍历某个表的所有配置
//	public List<ISQLExtentionCRUDDao<T,E>> getTableAllDaos()
//	{
//		return holder.getTableAllDaos(tablePrefixName);
//	}
//	
//	@Override
//	public P findByCondition(Integer start, Integer end, Map<String, Object> condition)
//	{
//		return null;
//	}
//
//	@Override
//	public P findAllByPage(Integer start, Integer end)
//	{
//		List<ISQLExtentionCRUDDao<T, E>> daos = getTableAllDaos();
//		for (ISQLExtentionCRUDDao<T, E> dao : daos)
//		{
//			PageExample pageExample=new PageExample();
//			pageExample.setStart(start);
//			pageExample.setEnd(end);
//			List<T> datas = dao.selectByExample((E) pageExample);
//			
//		}
//		return null;
//	}
//	public P doFind(E example,Integer start,Integer end)
//	{
//		Long startTime = System.currentTimeMillis();
//		log.info("[findAllByPage]分页查询开始查询数据");
//		Integer avgStart = start / 2;
//		Criteria criteria = example.createCriteria();
//		example.setOrderByClause(" category_id asc ");
//		List<TmallCategory> categories = db0categoryDao.selectByExample(example);
//		List<TmallCategory> db1Categories = db1CategoryDao.selectByExample(example);
//		Integer minCategoryId = null;
//		if (null == categories && null == db1Categories)
//		{
//			return Collections.emptyList();
//		}
//		Long firstSearchTime = System.currentTimeMillis();
//		log.info("[findAllByPage]分页查询第一次查询数据,耗时:{}毫秒", firstSearchTime - startTime);
//		Integer db0MinCatId = Integer.MAX_VALUE;
//		Integer db1MinCatId = Integer.MAX_VALUE;
//		Integer db0MaxCatId = 0;
//		Integer db1MaxCatId = 0;
//		Boolean isDb0Min = false;
//		// 用于存放
//		// List<Integer>dbSqlAgainList=new ArrayList<>();
//		if (!categories.isEmpty())
//		{
//			db0MinCatId = categories.iterator().next().getCategoryId();
//			db0MaxCatId = categories.get(categories.size() - 1).getCategoryId();
//		}
//		if (!db1Categories.isEmpty())
//		{
//			db1MinCatId = db1Categories.iterator().next().getCategoryId();
//			db1MaxCatId = db1Categories.get(db1Categories.size() - 1).getCategoryId();
//		}
//		// minCategoryId=db0MinCatId<db1MinCatId?{db0MinCatId;isDb0Min=true;:db1MinCatId;
//		if (db0MinCatId < db1MinCatId)
//		{
//			minCategoryId = db0MinCatId;
//			isDb0Min = true;
//		} else
//		{
//			minCategoryId = db1MinCatId;
//		}
//		// 3.进行二次查询,并且是条件查询了,,因为这里只有2个表,后期表多了的话可以用map存储,而不是用ifelse 来判断了
//		List<TmallCategory> db1Categories2 = null;
//		List<TmallCategory> db0Categories2 = null;
//		// List<List<TmallCategory>>storeList=new ArrayList<>();
//		// 如果db0中查询得到的数据最小的话则db0不用查询,查询db1
//		example.setStart(null);
//		example.setEnd(null);
//		if (isDb0Min)
//		{
//			criteria.andCategoryIdBetween(minCategoryId, db1MaxCatId);
//			db1Categories2 = db1CategoryDao.selectByExample(example);
//			db0Categories2 = categories;
//		} else
//		{
//			criteria.andCategoryIdBetween(minCategoryId, db0MaxCatId);
//			db0Categories2 = db0categoryDao.selectByExample(example);
//			db1Categories2 = db1Categories;
//		}
//		Long secondSearchTime = System.currentTimeMillis();
//		log.info("[findAllByPage]分页查询第二次查询数据,耗时:{}毫秒", secondSearchTime - firstSearchTime);
//		// storeList.add(db0Categories2);
//		// storeList.add(db1Categories2);
//		// 4.对数据进行解析,计算得出minCatId在全局中的偏移量
//		int offSet = 0;
//		// for (List<TmallCategory> list : storeList)
//		// {
//		// if(list.isEmpty())
//		// {
//		// continue;
//		// }
//		// if(list.iterator().next().getCategoryId().equals(minCategoryId))
//		// {
//		// offSet+=avgStart;
//		// }else {
//		// offSet+=avgStart-()
//		// }
//		// }
//		if (!db0Categories2.isEmpty())
//		{
//			if (db0Categories2.iterator().next().getCategoryId().equals(minCategoryId))
//			{
//				offSet += avgStart;
//			} else
//			{
//				offSet += avgStart - (db0Categories2.size() - categories.size());
//			}
//		}
//		if (!db1Categories2.isEmpty())
//		{
//			if (db1Categories.iterator().next().getCategoryId().equals(minCategoryId))
//			{
//				offSet += avgStart;
//			} else
//			{
//				offSet += avgStart - (db1Categories2.size() - db1Categories.size());
//			}
//		}
//		// 5.得到数据偏移量之后就可以排序,然后得到数据了:先遍历到minCatId的位置(第一个位置就是),然后移动start-偏移量之后,开始取数据,取end条数据(pageSize)
//		db1Categories2.addAll(db0Categories2);
//		ProductServerUtil.sortByCategoryId(db1Categories2);
//		List<TmallCategory> res = new ArrayList<>();
//		for (int i = (start - offSet); i < end; i++)
//		{
//			res.add(db1Categories2.get(i));
//		}
//		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
//		res.forEach(c -> {
//			dtos.add(c.to());
//		});
//		log.info("[findAllByPage]分页查询结束,总耗时:{}毫秒", System.currentTimeMillis() - startTime);
//		return dtos;
//	}
//
//	@Override
//	public Long countByCondition(Map<String, Object> condition)
//	{
//		return null;
//	}
//	
//}
