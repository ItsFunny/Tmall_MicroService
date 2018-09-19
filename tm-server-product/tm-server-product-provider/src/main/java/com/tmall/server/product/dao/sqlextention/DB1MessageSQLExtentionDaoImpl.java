/**
*
* @author joker 
* @date 创建时间：2018年9月16日 下午5:52:15
* 
*/
package com.tmall.server.product.dao.sqlextention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmall.common.model.MessageModel;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.common.other.SQLExtentionHolder;
import com.tmall.server.product.dao.db1.DB1MessageDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月16日 下午5:52:15
*/
@Repository(value="db1MessageDaoImpl")
public class DB1MessageSQLExtentionDaoImpl implements ISQLExtentionCRUDDao<MessageModel>
{
	@Autowired
	private DB1MessageDao db1MessageDao;
	
	@Override
	public Integer insertSelective(String tableName, Object t)
	{
		MessageModel messageModel=(MessageModel) t;
		messageModel.setTableName(tableName);
		return db1MessageDao.insert(messageModel);
	}

	@Override
	public Integer updateSelectTive(String tableName, Object t, Object example)
	{
		return null;
	}

	@Override
	public MessageModel findByPrimaryKey(Number id)
	{
		return null;
	}

	@Override
	public List<MessageModel> findByExample(Object example)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteInBatchByExample(Object example)
	{
		return null;
	}

}
