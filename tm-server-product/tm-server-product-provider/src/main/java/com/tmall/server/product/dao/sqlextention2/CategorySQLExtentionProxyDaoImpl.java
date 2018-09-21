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
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;
import com.tmall.server.product.common.model.TmallCategoryExample.Criteria;
import com.tmall.server.product.dao.TmallCategoryDao;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月21日 下午4:29:51
 */
@Component(value="categorySQLExtentionProxyDaoImpl")
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
	public Integer updateSelectiveByExample(TmallCategory t, Object example)
	{
		Number key = t.getUniquekey();
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = getDetailConfigDao(key);
		return dao.updateSelectiveByExample(t, example);
	}


	@Override
	public TmallCategory selectByExample(Number key,Object example)
	{
		ISQLExtentionBaseCRUDDao<TmallCategory> dao = getDetailConfigDao(key);
		return dao.selectByExample(key, example);
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
		TmallCategoryExample example=new TmallCategoryExample();
		example.setTableName(tableName);
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo((Integer) primaryKey);
		return dao.selectByExample(primaryKey, example);
	}

}
