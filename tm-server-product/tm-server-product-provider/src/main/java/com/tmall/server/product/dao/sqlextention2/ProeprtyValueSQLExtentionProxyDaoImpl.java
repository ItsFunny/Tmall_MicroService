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
import org.springframework.stereotype.Repository;

import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
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
@Repository(value="proeprtyValueSQLExtentionProxyDaoImpl")
public class ProeprtyValueSQLExtentionProxyDaoImpl implements ISQLExtentionProxyBaseCRUDDao<TmallPropertyValue>
{
	@Autowired
	private List<TmallPropertyValueDao>daos;

	@Override
	public ISQLExtentionBaseCRUDDao<TmallPropertyValue> getDetailConfigDao(Number uniqueKey)
	{
		int index=(int) (uniqueKey.longValue()%daos.size());
		return daos.get(index);
	}

	@Override
	public List<? extends ISQLExtentionBaseCRUDDao<TmallPropertyValue>> getAllDaos()
	{
		return this.daos;
	}

}
