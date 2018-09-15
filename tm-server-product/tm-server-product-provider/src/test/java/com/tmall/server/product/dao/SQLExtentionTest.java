/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午5:43:26
* 
*/
package com.tmall.server.product.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.utils.DateUtils;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.common.other.SQLExtentionDAOWrapper;
import com.tmall.common.other.SQLExtentionHolder;
import com.tmall.server.product.common.model.TmallCategory;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午5:43:26
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class SQLExtentionTest
{
	@Autowired
	private SQLExtentionHolder holder;
	
	@Test
	public void testInsert()
	{
		TmallCategory category=new TmallCategory();
		category.setCategoryDisplaySeq(1);
		category.setCategoryId(9999);
		category.setCategoryName("ceshi ");
		category.setCategoryPid(1);
		category.setCreateDate(new Date());
		category.setCreateTime(((Number)DateUtils.getCurrentDay()).intValue());
		category.setCreator("joker");
		category.setCreatorUserId(1L);
		SQLExtentionDAOWrapper ba = holder.getBaseDao(SQLExtentionConstant.CATEGORY, 1L);
		ISQLExtentionCRUDDao dao = ba.getBaseDao();
		Integer insert = dao.insertSelective(ba.getTableDetailName(), category);
		System.out.println(insert);
	}

}
