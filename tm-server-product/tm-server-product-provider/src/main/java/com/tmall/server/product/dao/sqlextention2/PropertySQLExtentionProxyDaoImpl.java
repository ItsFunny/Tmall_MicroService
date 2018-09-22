/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午1:58:30
* 
*/
package com.tmall.server.product.dao.sqlextention2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.dao.TmallPropertyDao;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午1:58:30
 */
@Repository("propertySQLExtentionProxyDaoImpl")
public class PropertySQLExtentionProxyDaoImpl
		implements ISQLExtentionProxyBaseCRUDDao<TmallProperty>, ISQLExtentionBaseCRUDDao<TmallProperty>
{
	@Autowired
	private List<TmallPropertyDao> daos;

	@Override
	public ISQLExtentionBaseCRUDDao<TmallProperty> getDetailConfigDao(Number uniqueKey)
	{
		int index = (int) (uniqueKey.longValue() % daos.size());
		return daos.get(index);
	}

	@Override
	public List<? extends ISQLExtentionBaseCRUDDao<TmallProperty>> getAllDaos()
	{
		return this.daos;
	}

	@Override
	public Integer insertSelective(TmallProperty t)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBatchSelective(String tableName,List<TmallProperty> list)
	{
		
		return 0;
	}

	@Override
	public int updateByExampleSelective(TmallProperty record, Object example)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TmallProperty> selectByExample(Object example)
	{
		// TODO Auto-generated method stub
		return null;
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
	public int insert(TmallProperty record)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TmallProperty selectByPrimaryKey(String tableName, Number primaryKey)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
