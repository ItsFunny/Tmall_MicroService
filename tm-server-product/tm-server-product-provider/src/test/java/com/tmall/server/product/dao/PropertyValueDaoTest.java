/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月24日 上午11:51:37
* 
*/
package com.tmall.server.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.server.product.TmallProductServerApplication;
import com.tmall.server.product.common.model.TmallPropertyValue;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月24日 上午11:51:37
*/
@SpringBootTest(classes =
{ TmallProductServerApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class PropertyValueDaoTest
{

	@Autowired
	private SQLExtentionHolderV3 holder;
	@Test
	public void testBatchinsertWithUpdate()
	{
		long id=408001645587468288l;
		SQLExtentionDaoWrapper<TmallPropertyValue> wrapper = holder.getBaseDao(SQLExtentionConstant.PROPERTY_VALUE,id);
		List<TmallPropertyValue>values=new ArrayList<TmallPropertyValue>();
		TmallPropertyValue value=new TmallPropertyValue();
		value.setPropertyDisSeq(100);
		value.setPropertyValue("asdd");
		value.setPropertyValueId(id);
		value.setPropertyId(1);
		values.add(value);
		int i = wrapper.getDao().insertBatchSelective(wrapper.getTableName(), values);
		System.out.println(i);
		
		
	}
}
