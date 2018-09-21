/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午11:35:01
* 
*/
package com.tmall.server.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.AbstractPageService;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.service.IdWorkerService;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.CategoryDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.other.ISQLExtentionBaseCRUDService;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
import com.tmall.server.product.exception.TmallProductException;
import com.tmall.server.product.service.ICategoryService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 下午11:35:01
*/
public class CategoryServiceImpl2 extends AbstractPageService<List<CategoryDTO>> implements ICategoryService
{
	@Autowired
	private SQLExtentionHolderV3 holder;
	@Autowired
	private IdWorkerService idWorkerService;

	@Override
	public Integer insert(TmallCategory category)
	{
		 SQLExtentionDaoWrapper<AbstractSQLExtentionModel> wrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY,category.getUniquekey());
		 ISQLExtentionBaseCRUDDao<AbstractSQLExtentionModel> dao = wrapper.getDao();
		 category.setTableName(wrapper.getTableName());
		return dao.insertSelective(category);
	}

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
			TmallCategoryExample example = new TmallCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(category.getCategoryId());
			SQLExtentionDaoWrapper<TmallCategory> daoWP = holder.getBaseDao(SQLExtentionConstant.CATEGORY, category.getUniquekey());
			ISQLExtentionBaseCRUDDao<TmallCategory> dao = daoWP.getDao();
			String tableName = daoWP.getTableName();
			category.setTableName(tableName);
			validCount=dao.updateByExampleSelective(category, example);
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
	public CategoryDTO findByCategoryId(Integer categoryId)
	{
		SQLExtentionDaoWrapper<TmallCategory> wrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY, categoryId);
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = wrapper.getDao();
		TmallCategory category = dao.selectByPrimaryKey(SQLExtentionConstant.CATEGORY,categoryId);
		if (null != category)
		{
			return category.to();
		} else
		{
			return null;
		}
	}

	@Override
	public CategoryDTO findCategoryFathers(Integer categoryId)
	{
		return null;
	}

	@Override
	public List<CategoryDTO> findCategoryChilds(Integer categoryPid)
	{
		return null;
	}

	@Override
	public List<CategoryDTO> findCategoriesOnConditionWithOutPage(Map<String, Object> condition)
	{
		return null;
	}

	@Override
	public ResultDTO<Integer> deleteInBatch(UserRecordAspectWrapper<List<Integer>> wrapper)
	{
		return null;
	}

	@Override
	public ResultDTO<Integer> updateStatus(UserRecordAspectWrapper<CategoryDTO> wrapper)
	{
		return null;
	}

	@Override
	protected List<CategoryDTO> findSingleById(Object id)
	{
		return null;
	}

	@Override
	public List<CategoryDTO> findByCondition(Integer start, Integer end, Map<String, Object> condition)
	{
		return null;
	}

	@Override
	public List<CategoryDTO> findAllByPage(Integer start, Integer end)
	{
		return null;
	}

	@Override
	public Long countByCondition(Map<String, Object> condition)
	{
		return null;
	}

	
	
}
