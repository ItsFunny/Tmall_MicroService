/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午3:55:44
* 
*/
package com.tmall.server.product.dao.sqlextention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.dao.db1.Db1CategoryDao;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午3:55:44
*/
@Repository(value="db1DaoImpl")
public class DB1CategorySQLExtentionDaoImpl implements ISQLExtentionCRUDDao
{
	@Autowired
	private Db1CategoryDao db1CategoryDao;

	@Override
	public Integer insertSelective(String tableName, Object t)
	{
		TmallCategory category=(TmallCategory) t;
		category.setTableName(tableName);
		return db1CategoryDao.insertSelective(category);
	}

}
