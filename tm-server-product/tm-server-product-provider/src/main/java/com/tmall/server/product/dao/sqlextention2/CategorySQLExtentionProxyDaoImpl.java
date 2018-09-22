/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午4:29:51
* 
*/
package com.tmall.server.product.dao.sqlextention2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
import com.tmall.server.product.dao.TmallCategoryDao;
import com.tmall.server.product.exception.TmallProductException;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月21日 下午4:29:51
 */
@Component(value = "categorySQLExtentionProxyDaoImpl")
public class CategorySQLExtentionProxyDaoImpl
		implements ISQLExtentionProxyBaseCRUDDao<TmallCategory>, ISQLExtentionBaseCRUDDao<TmallCategory>
{
	@Autowired
	private List<TmallCategoryDao> daos;

	@Override
	public ISQLExtentionBaseCRUDDao<TmallCategory> getDetailConfigDao(Number uniqueKey)
	{
		return daos.get((int) (uniqueKey.longValue() % daos.size()));
	}

	@Override
	public Integer insertSelective(TmallCategory t)
	{
		Number key = t.getUniquekey();
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = getDetailConfigDao(key);
		return dao.insertSelective(t);
	}

	@Override
	public long countByExample(Object example)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(Object example)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer uniquekey)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TmallCategory record)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TmallCategory selectByPrimaryKey(String tableName, Number primaryKey)
	{
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = getDetailConfigDao(primaryKey);
		TmallCategoryExample example = new TmallCategoryExample();
		example.setTableName(tableName);
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo((Integer) primaryKey);
		List<TmallCategory> res = dao.selectByExample(example);
		if (res == null || res.isEmpty())
		{
			return null;
		} else if (res.size() > 1)
		{
			throw new TmallProductException(ErrorCodeEnum.FIND_MULTIPLE_RECORDS);
		} else
		{
			return res.iterator().next();
		}
	}

	@Override
	public List<? extends ISQLExtentionBaseCRUDDao<TmallCategory>> getAllDaos()
	{
		return this.daos;
	}

	@Override
	public List<TmallCategory> selectByExample(Object example)
	{
		return null;
	}

	@Override
	public int updateByExampleSelective(TmallCategory record, Object example)
	{
		Number key = record.getUniquekey();
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = getDetailConfigDao(key);
		return dao.updateByExampleSelective(record, example);
	}

	@Override
	public int insertBatchSelective(String tableName,List<TmallCategory> list)
	{
		return 0;
	}

}
