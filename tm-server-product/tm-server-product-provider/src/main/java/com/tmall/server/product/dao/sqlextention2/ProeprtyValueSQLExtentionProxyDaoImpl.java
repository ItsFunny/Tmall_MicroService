/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午2:01:35
* 
*/
package com.tmall.server.product.dao.sqlextention2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.AbstractSQLExtentionpProxyBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.joker.library.sqlextention.SQLExtentionInfo.DBInfo;
import com.tmall.server.product.common.model.TmallPropertyValue;
import com.tmall.server.product.dao.TmallPropertyValueDao;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午2:01:35
 */
@Repository(value = "proeprtyValueSQLExtentionProxyDaoImpl")
public class ProeprtyValueSQLExtentionProxyDaoImpl extends AbstractSQLExtentionpProxyBaseCRUDDao<TmallPropertyValue>
//public class ProeprtyValueSQLExtentionProxyDaoImpl implements ISQLExtentionProxyBaseCRUDDao<TmallPropertyValue>
{
	@Autowired
	private List<TmallPropertyValueDao> daos;

	@Override
	public ISQLExtentionBaseCRUDDao<TmallPropertyValue> getDetailConfigDao(Number uniqueKey)
	{
		int index = (int) (uniqueKey.longValue() % daos.size());
		return daos.get(index);
	}

	@Override
	public List<? extends ISQLExtentionBaseCRUDDao<TmallPropertyValue>> getAllDaos()
	{
		return this.daos;
	}

	@Override
	public Integer insertSelective(TmallPropertyValue t)
	{
		return null;
	}


	@Override
	public int updateByExampleSelective(TmallPropertyValue record, Object example)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TmallPropertyValue> selectByExample(Object example)
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
	public int insert(TmallPropertyValue record)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TmallPropertyValue selectByPrimaryKey(String tableConcreteName, Number primaryKey)
	{
		// TODO Auto-generated method stub
		return null;
	}



}
