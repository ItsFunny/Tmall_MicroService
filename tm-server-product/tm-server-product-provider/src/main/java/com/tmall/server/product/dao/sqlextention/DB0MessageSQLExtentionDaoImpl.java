/**
*
* @author joker 
* @date 创建时间：2018年9月16日 下午5:50:22
* 
*/
package com.tmall.server.product.dao.sqlextention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmall.common.model.MessageModel;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.server.product.dao.db0.DB0MessageDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月16日 下午5:50:22
*/
@Repository(value="db0MessageDaoImpl")
public class DB0MessageSQLExtentionDaoImpl implements ISQLExtentionCRUDDao<MessageModel>
{
	@Autowired
	private DB0MessageDao db0MessageDao;

	@Override
	public Integer insertSelective(String tableName, Object t)
	{
		MessageModel messageModel=(MessageModel) t;
		messageModel.setTableName(tableName);
		return db0MessageDao.insert(messageModel);
	}

	@Override
	public Integer updateSelectTive(String tableName, Object t, Object example)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageModel findByPrimaryKey(Number id)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
