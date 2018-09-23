/**
*
* @author joker 
* @date 创建时间：2018年9月13日 下午1:43:10
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
import com.joker.library.service.IdWorkerService;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.joker.library.sqlextention.SQLExtentionInfo.TableInfo;
import com.tmall.common.annotation.RabbitMQTransaction;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
import com.tmall.server.product.exception.TmallProductException;
import com.tmall.server.product.service.ICategoryService;
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
public class CategoryServiceImpl extends AbstractMultipartDBPageService<TmallCategory,TmallCategoryExample> implements ICategoryService
{

	@Autowired
	private IdWorkerService idWorkerService;

	@Autowired
	private SQLExtentionHolderV3 holder;

	@Override
	protected  Long countByCondition(DBInfo<TmallCategory>[] dbs, TmallCategoryExample example)
	{
		long count = 0l;
//		TmallCategoryExample example = (TmallCategoryExample) exampleObj;
		Criteria criteria = example.createCriteria();
		for (DBInfo<TmallCategory> dbInfo : dbs)
		{
			ISQLExtentionBaseCRUDDao<TmallCategory> dao = dbInfo.getDao();
			TableInfo<TmallCategory>[] tables = dbInfo.getTables();
			for (TableInfo<TmallCategory> tableInfo : tables)
			{
				example.setTableName(tableInfo.getTableName());
				count += dao.countByExample(example);
			}
		}
		return count;
	}

	@Override
	protected List<TmallCategory> doFindByExample(String tableName,
			ISQLExtentionBaseCRUDDao<? extends AbstractSQLExtentionModel> dao, Integer avgStart, Integer end,
			Object exampleObj)
	{
		TmallCategoryExample example = (TmallCategoryExample) exampleObj;
		example.setTableName(tableName);
		if (null != avgStart)
		{
			example.setStart(avgStart);
		}
		if (null != end)
		{
			example.setEnd(end);
		}
		return (List<TmallCategory>) dao.selectByExample(example);
	}

	@Override
	protected Long getMinId(List<List<TmallCategory>> list)
	{
		Long minId = Long.MAX_VALUE;
		for (List<TmallCategory> list2 : list)
		{
			if(list2.isEmpty())
			{
				continue;
			}
			long cid = list2.get(0).getCategoryId();
			minId = minId < cid ? minId : cid;
		}
		return minId;
	}

	@Override
	protected void getMaxId(List<Long> maxIdList, List<List<TmallCategory>> totalList)
	{
		for (List<TmallCategory> list : totalList)
		{
			if(null==list || list.isEmpty())
			{
				//如果为空,说明这个表中已经无记录了,不能跳过,
				maxIdList.add((long) Integer.MAX_VALUE);
				continue;
			}
			maxIdList.add(((Number) list.get(list.size() - 1).getCategoryId()).longValue());
		}
	}

	@Override
	protected  List<TmallCategory> secondFindByBetween(String concreteTableName,
			ISQLExtentionBaseCRUDDao<TmallCategory> dao, long min, long max, Map<String, Object>condition)
	{
//		TmallCategoryExample example = new TmallCategoryExample();
//		example=(TmallCategoryExample) exampleObj;
//		example.setStart(null);
//		example.setEnd(null);
//		example.setTableName(concreteTableName);
//		Criteria criteria = example.createCriteria();
//		criteria.andCategoryIdBetween((int) min, (int) max);
//		TmallCategoryExample example2=new TmallCategoryExample();
//		example2.setTableName(concreteTableName);
//		Criteria criteria2 = example2.createCriteria();
//		criteria2.andCategoryIdBetween((int)min,(int)max);
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		if (condition.containsKey("searchKey"))
		{
			example.or().andCategoryNameLike("%" + condition.get("searchKey").toString() + "%");
		}
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
		example.setTableName(concreteTableName);
		criteria.andCategoryIdBetween((int)min,(int)max);
		return dao.selectByExample(example);
	}

	@Override
	public Integer insert(TmallCategory category)
	{
		SQLExtentionDaoWrapper<AbstractSQLExtentionModel> wrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY,
				category.getUniquekey());
		category.setTableName(wrapper.getTableName());
		return wrapper.getDao().insertSelective(category);
	}

	@Override
	public CategoryDTO findByCategoryId(Integer categoryId)
	{
		SQLExtentionDaoWrapper<TmallCategory> wrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY, categoryId);
		TmallCategory category = wrapper.getDao().selectByPrimaryKey(wrapper.getTableName(), categoryId);
		if (null != category)
		{
			return category.to();
		} else
		{
			return null;
		}
	}

	/*
	 * 应该有好办法的,而不是递归的去数据库搜索
	 */
	@Override
	public CategoryDTO findCategoryFathers(Integer categoryId)
	{

		CategoryDTO categoryDTO = findByCategoryId(categoryId);
		if (null != categoryDTO)
		{
			if (null != categoryDTO.getCategoryPid())
			{
				loopFind(categoryDTO, categoryDTO.getCategoryPid());
			}
			return categoryDTO;
		} else
		{
			return null;
		}

	}

	private void loopFind(CategoryDTO parent, Integer categoryId)
	{
		CategoryDTO categoryDTO = findByCategoryId(categoryId);
		if (null != categoryDTO)
		{
			parent.setParent(categoryDTO);
			if (categoryDTO.getCategoryPid() == null)
			{
				return;
			}
			Integer pid = categoryDTO.getCategoryPid();
			loopFind(categoryDTO, pid);
		} else
		{
			return;
		}
	}

	@RabbitMQTransaction
	@Override
	public ResultDTO<String> insertOrUpdate(UserRecordAspectWrapper<CategoryDTO> wrapper)
	{
		CategoryDTO dto = wrapper.getData();
		TmallCategory category = new TmallCategory();
		category.from(dto);
		Integer validCount = null;
		if (null == category.getCategoryId())
		{
			category.setCategoryId(((Number) idWorkerService.nextId()).intValue());
			validCount = insert(category);
		} else
		{
			SQLExtentionDaoWrapper<TmallCategory> sqlWrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY,
					category.getUniquekey());
			// InnerSQLExtentionCRUDServiceImpl impl = this.new
			// InnerSQLExtentionCRUDServiceImpl();
			TmallCategoryExample example = new TmallCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(category.getCategoryId());
			// validCount = impl.update(SQLExtentionConstant.CATEGORY,
			// category.getCategoryId(), category, example);
			validCount = sqlWrapper.getDao().updateByExampleSelective(category, example);
		}
		if (validCount > 0)
		{
			return ResultUtils.sucess();
		} else
		{
			throw new TmallProductException(ErrorCodeEnum.INTERNAL_DB_ERROR);
		}

	}

	@Override
	public List<CategoryDTO> findCategoryChilds(Integer categoryPid)
	{
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryPidEqualTo(categoryPid);
		// InnerSQLExtentionCRUDServiceImpl impl = this.new
		// InnerSQLExtentionCRUDServiceImpl();
		List<? extends ISQLExtentionBaseCRUDDao<TmallCategory>> daos = holder.getAllDaos(SQLExtentionConstant.CATEGORY);
		List<TmallCategory> categories = new ArrayList<>();
		for (ISQLExtentionBaseCRUDDao<TmallCategory> dao : daos)
		{
			categories.addAll(dao.selectByExample(example));
		}
		// List<TmallCategory> categories = impl.findByExample(example);
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		if (null != categories)
		{
			categories.forEach(c -> {
				categoryDTOs.add(c.to());
			});
		}
		return categoryDTOs;

	}

	@Override
	public List<CategoryDTO> findCategoriesOnConditionWithOutPage(Map<String, Object> condition)
	{
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		if (condition.containsKey("categoryPid"))
		{
			criteria.andCategoryPidEqualTo(Integer.parseInt(condition.get("categoryPid").toString()));
		}

		// InnerSQLExtentionCRUDServiceImpl impl = new
		// InnerSQLExtentionCRUDServiceImpl();
		List<? extends ISQLExtentionBaseCRUDDao<TmallCategory>> daos = holder.getAllDaos(SQLExtentionConstant.CATEGORY);
		List<TmallCategory> categories = new ArrayList<>();
		for (ISQLExtentionBaseCRUDDao<TmallCategory> dao : daos)
		{
			categories.addAll(dao.selectByExample(example));
		}
		// List<TmallCategory> categories = impl.findByExample(example);
		List<CategoryDTO> dtos = new ArrayList<>();
		if (null != categories)
		{
			categories.forEach(c -> {
				dtos.add(c.to());
			});
		}

		return dtos;
	}

	@RabbitMQTransaction
	@Override
	public ResultDTO<Integer> deleteInBatch(UserRecordAspectWrapper<List<Integer>> wrapper)
	{
		List<Integer> idList = wrapper.getData();
		if (null == idList || idList.isEmpty())
		{
			throw new TmallProductException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.ILLEGAL_ARGUMENT, "参数Id不能为空"));
		}
		log.info("[deleteInBatch]批量删除类目:{}", idList);
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdIn(idList);
		// InnerSQLExtentionCRUDServiceImpl impl = new
		// InnerSQLExtentionCRUDServiceImpl();
		List<? extends ISQLExtentionBaseCRUDDao<TmallCategory>> daos = holder.getAllDaos(SQLExtentionConstant.CATEGORY);
		Integer validCount = 0;
		for (ISQLExtentionBaseCRUDDao<TmallCategory> dao : daos)
		{
			validCount += dao.deleteByExample(example);
		}
		// = impl.deleteInBatchByExample(example);
		if (validCount != idList.size())
		{
			log.error("[deleteInBatch]批量删除类目成功的数目与预期的不一致,成功删除:{}条记录,预期:{}条记录", validCount, idList.size());
			throw new TmallProductException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.ILLEGAL_DB_RESULT, "操作成功记录数与预期的不一致"));
		}
		return ResultUtils.sucess(validCount);
	}

	@RabbitMQTransaction
	@Override
	public ResultDTO<Integer> updateStatus(UserRecordAspectWrapper<CategoryDTO> wrapper)
	{
		log.info("[updateStatus]更新店铺状态,wrapper:{}", wrapper);
		CategoryDTO categoryDTO = wrapper.getData();
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryDTO.getCategoryId());
		TmallCategory category = new TmallCategory();
		category.setStatus(categoryDTO.getStatus());
		category.setCategoryId(categoryDTO.getCategoryId());
		// InnerSQLExtentionCRUDServiceImpl impl = new
		// InnerSQLExtentionCRUDServiceImpl();
		SQLExtentionDaoWrapper<TmallCategory> sqlWrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY,
				category.getCategoryId());
		category.setTableName(sqlWrapper.getTableName());
		int validCount = sqlWrapper.getDao().updateByExampleSelective(category, example);
		// Integer validCount = impl.update(SQLExtentionConstant.CATEGORY,
		// category.getCategoryId(), category, example);
		if (validCount > 0)
		{
			return ResultUtils.sucess();
		} else
		{
			log.error("[updateStatus]本地更新店铺状态失败,生效记录数为0");
			throw new TmallProductException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.INTERNAL_DB_ERROR, "本地更新数据失败"));
		}
	}

	@Override
	protected TmallCategoryExample getExample(Map<String, Object> condition)
	{
		TmallCategoryExample example = new TmallCategoryExample();
		Criteria criteria = example.createCriteria();
		if (condition.containsKey("searchKey"))
		{
			example.or().andCategoryNameLike("%" + condition.get("searchKey").toString() + "%");
		}
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
		return example;
	}




}
