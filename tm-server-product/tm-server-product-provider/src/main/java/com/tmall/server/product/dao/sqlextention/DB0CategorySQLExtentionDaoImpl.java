/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午3:49:46
* 
*/
package com.tmall.server.product.dao.sqlextention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.dao.db0.Db0CategoryDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午3:49:46
*/
@Repository(value="db0DaoImpl")
public class DB0CategorySQLExtentionDaoImpl implements ISQLExtentionCRUDDao<TmallCategory>
{
	@Autowired
	private Db0CategoryDao db0CategoryDao;

	@Override
	public Integer insertSelective(String tableName, Object t)
	{
		TmallCategory category=(TmallCategory) t;
		category.setTableName(tableName);
		return db0CategoryDao.insertSelective(category);
	}

	@Override
	public TmallCategory findByPrimaryKey(Number id)
	{
		return db0CategoryDao.selectByPrimaryKey(id.intValue());
	}

	@Override
	public Integer updateSelectTive(String tableName, Object t,Object example)
	{
		return db0CategoryDao.updateByExampleSelective((TmallCategory) t, (TmallCategoryExample)example);
	}

	@Override
	public List<TmallCategory> findByExample(Object example)
	{
		TmallCategoryExample example2=(TmallCategoryExample) example;
		return db0CategoryDao.selectByExample(example2);
	}

//	@Override
//	public List<TmallCategory> selectByExample(TmallCategoryExample e)
//	{
//		return db0CategoryDao.selectByExample(e);
//	}


}
