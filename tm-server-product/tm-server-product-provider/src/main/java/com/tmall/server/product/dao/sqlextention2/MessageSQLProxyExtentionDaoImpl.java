/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午8:58:45
* 
*/
package com.tmall.server.product.dao.sqlextention2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.ISQLExtentionProxyBaseCRUDDao;
import com.tmall.common.model.MessageModel;
import com.tmall.server.product.dao.MessageDao;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午8:58:45
*/
@Component(value="messageSQLExtentionDaoImpl")
public class MessageSQLProxyExtentionDaoImpl implements ISQLExtentionProxyBaseCRUDDao<MessageModel>
{
	@Autowired
	private List<MessageDao>daos;

	@Override
	public ISQLExtentionBaseCRUDDao<MessageModel> getDetailConfigDao(Number uniqueKey)
	{
		return daos.get((int) (uniqueKey.longValue()%daos.size()));
	}

	@Override
	public List<? extends ISQLExtentionBaseCRUDDao<MessageModel>> getAllDaos()
	{
		// TODO Auto-generated method stub
		return daos;
	}

}
