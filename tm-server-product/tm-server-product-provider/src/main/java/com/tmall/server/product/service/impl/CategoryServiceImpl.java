/**
*
* @author joker 
* @date 创建时间：2018年9月13日 下午1:43:10
* 
*/
package com.tmall.server.product.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.page.AbstractPageService;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
import com.tmall.server.product.dao.db0.Db0CategoryDao;
import com.tmall.server.product.dao.db1.Db1CategoryDao;
import com.tmall.server.product.service.ICategoryService;
import com.tmall.server.product.util.ProductServerUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 分页查询:如果条件为空,表明是分页查询所有的记录,否则 可以通过category_pid,status,create_time,
 * like查询:category_name
 * 
 * @author joker
 * @date 创建时间：2018年9月13日 下午1:43:10
 */
@Slf4j
@Service
public class CategoryServiceImpl extends AbstractPageService<List<CategoryDTO>> implements ICategoryService
{

	@Autowired
	private Db0CategoryDao db0categoryDao;
	@Autowired
	private Db1CategoryDao db1CategoryDao;

	@Override
	public List<CategoryDTO> findByCondition(Integer start, Integer end, Map<String, Object> condition)
	{
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		if (condition.containsKey("categoryName"))
		{
			example.or().andCategoryNameLike(condition.get("categoryName").toString());
		}
		if (condition.containsKey("categoryPid"))
		{
			criteria.andCategoryPidEqualTo(Integer.parseInt(condition.get("categoryPid").toString()));
		}
		if (condition.containsKey("status"))
		{
			criteria.andStatusEqualTo(Integer.parseInt(condition.get("status").toString()));
		}
		List<TmallCategory> categories = db0categoryDao.selectByExample(example);
		List<CategoryDTO> dtos = new ArrayList<>();
		categories.forEach(c -> {
			dtos.add(c.to());
		});
		return dtos;
	}
	@Override
	public List<CategoryDTO> findAllByPage(Integer start, Integer end)
	{
		TmallCategoryExample example = new TmallCategoryExample();
		Long startTime=System.currentTimeMillis();
		log.info("[findAllByPage]分页查询开始查询数据");
		/*
		 * 查询的逻辑
		 * 1.先获取要查询的起始点,在这里start就是起始点,然后计算有多少个表,除以然后获取平均值
		 */
		Integer avgStart=start/2;//这里2最好是需要从配置中取,而不是这样写死
		//2.查询记录,并且按id的值升序,从而最小的是第一个
		example.setStart(avgStart);
		example.setEnd(end);
		Criteria criteria = example.createCriteria();
		example.setOrderByClause(" category_id asc ");
		List<TmallCategory> categories = db0categoryDao.selectByExample(example);
		List<TmallCategory> db1Categories = db1CategoryDao.selectByExample(example);
		Integer minCategoryId=null;
		if(null==categories &&null == db1Categories)
		{
			return Collections.emptyList();
		}
		Long firstSearchTime=System.currentTimeMillis();
		log.info("[findAllByPage]分页查询第一次查询数据,耗时:{}",firstSearchTime-startTime);
		Integer db0MinCatId=Integer.MAX_VALUE;
		Integer db1MinCatId=Integer.MAX_VALUE;
		Integer db0MaxCatId=0;
		Integer db1MaxCatId=0;
		Boolean isDb0Min=false;
		//用于存放
//		List<Integer>dbSqlAgainList=new ArrayList<>();
		if(!categories.isEmpty())
		{
			db0MinCatId=categories.iterator().next().getCategoryId();
			db0MaxCatId=categories.get(categories.size()-1).getCategoryId();
		}
		if(!db1Categories.isEmpty())
		{
			db1MinCatId=db1Categories.iterator().next().getCategoryId();
			db1MaxCatId=db1Categories.get(db1Categories.size()-1).getCategoryId();
		}
//		minCategoryId=db0MinCatId<db1MinCatId?{db0MinCatId;isDb0Min=true;:db1MinCatId;
		if(db0MinCatId<db1MinCatId)
		{
			minCategoryId=db0MinCatId;
			isDb0Min=true;
		}else {
			minCategoryId=db1MinCatId;
		}
		//3.进行二次查询,并且是条件查询了,,因为这里只有2个表,后期表多了的话可以用map存储,而不是用ifelse 来判断了
		List<TmallCategory>db1Categories2=null;
		List<TmallCategory>db0Categories2=null;
//		List<List<TmallCategory>>storeList=new ArrayList<>();
		//如果db0中查询得到的数据最小的话则db0不用查询,查询db1
		example.setStart(null);
		example.setEnd(null);
		if(isDb0Min)
		{
			criteria.andCategoryIdBetween(minCategoryId, db1MaxCatId);
			db1Categories2=db1CategoryDao.selectByExample(example);
			db0Categories2=categories;
		}else {
			criteria.andCategoryIdBetween(minCategoryId, db0MaxCatId);
			db0Categories2=db0categoryDao.selectByExample(example);
			db1Categories2=db1Categories;
		}
		Long secondSearchTime=System.currentTimeMillis();
		log.info("[findAllByPage]分页查询第二次查询数据,耗时:{}",secondSearchTime-firstSearchTime);
//		storeList.add(db0Categories2);
//		storeList.add(db1Categories2);
		//4.对数据进行解析,计算得出minCatId在全局中的偏移量
		int offSet=0;
//		for (List<TmallCategory> list : storeList)
//		{
//			if(list.isEmpty())
//			{
//				continue;
//			}
//			if(list.iterator().next().getCategoryId().equals(minCategoryId))
//			{
//				offSet+=avgStart;
//			}else {
//				offSet+=avgStart-()
//			}
//		}
		if(!db0Categories2.isEmpty())
		{
			if(db0Categories2.iterator().next().getCategoryId().equals(minCategoryId))
			{
				offSet+=avgStart;
			}else {
				offSet+=avgStart-(db0Categories2.size()-categories.size());
			}
		}
		if(!db1Categories2.isEmpty())
		{
			if(db1Categories.iterator().next().getCategoryId().equals(minCategoryId))
			{
				offSet+=avgStart;
			}else {
				offSet+=avgStart-(db1Categories2.size()-db1Categories.size());
			}
		}
		//5.得到数据偏移量之后就可以排序,然后得到数据了:先遍历到minCatId的位置(第一个位置就是),然后移动start-偏移量之后,开始取数据,取end条数据(pageSize)
		db1Categories2.addAll(db0Categories2);
		ProductServerUtil.sortByCategoryId(db1Categories2);
		List<TmallCategory>res=new ArrayList<>();
		for(int i=(start-offSet);i<end;i++)
		{
			res.add(db1Categories2.get(i));
		}
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		res.forEach(c -> {
			dtos.add(c.to());
		});
		log.info("[findAllByPage]分页查询结束,总耗时:{}",System.currentTimeMillis()-startTime);
		return dtos;
	}
	@Override
	public Long countByCondition(Map<String, Object> condition)
	{
		if (null == condition || condition.isEmpty())
		{
			return db0categoryDao.countCategory();
		}
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		if (condition.containsKey("categoryName"))
		{
			example.or().andCategoryNameLike(condition.get("categoryName").toString());
		}
		if (condition.containsKey("categoryPid"))
		{
			criteria.andCategoryPidEqualTo(Integer.parseInt(condition.get("categoryPid").toString()));
		}
		if (condition.containsKey("status"))
		{
			criteria.andStatusEqualTo(Integer.parseInt(condition.get("status").toString()));
		}
		long db0Count = db0categoryDao.countByExample(example);
		long db1Count = db1CategoryDao.countByExample(example);
		Long validCount=db0Count+db1Count;
		return validCount;
	}

}
