/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午3:49:46
* 
*/
package com.tmall.server.product.dao.sqlextention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.dao.db0.Db0CategoryDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午3:49:46
*/
@Repository(value="db0DaoImpl")
public class DB0CategorySQLExtentionDaoImpl implements ISQLExtentionCRUDDao
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

}
