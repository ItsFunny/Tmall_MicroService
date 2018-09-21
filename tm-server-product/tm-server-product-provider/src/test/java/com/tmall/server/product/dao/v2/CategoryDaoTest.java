/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午6:45:41
* 
*/
package com.tmall.server.product.dao.v2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.server.product.common.model.TmallCategory;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午6:45:41
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest
{
	@Autowired
	private SQLExtentionHolderV3 holder;
	
	@Test
	public void testFind()
	{
		SQLExtentionDaoWrapper<AbstractSQLExtentionModel> wrapper = holder.getBaseDao(SQLExtentionConstant.CATEGORY, 34);
		ISQLExtentionBaseCRUDDao<AbstractSQLExtentionModel> dao= wrapper.getDao();
		TmallCategory category = (TmallCategory) dao.selectByPrimaryKey(wrapper.getTableName(), 34);
		System.out.println(category);
	}

}
