/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午5:15:35
* 
*/
package com.tmall.server.product.dao.sqlextention;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallConfigException;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.dao.TmallPropertyDao;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 下午5:15:35
 */
@Component
public class TmallPropertySQLExtentionDaoImpl implements ISQLExtentionCRUDDao<TmallProperty>
{
	@Autowired
	private ArrayList<TmallPropertyDao> daos;

	@PostConstruct
	public void config()
	{
		for (int i = 0; i < daos.size(); i++)
		{
			Order order = daos.get(i).getClass().getAnnotation(Order.class);
			if (null == order)
			{
				throw new TmallConfigException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.CONFIG_ERROR, "dao必须拥有@Order接口"));
			}
			int index = order.value();
			if (index != i)
			{
				throw new TmallConfigException(
						ErrorCodeEnum.parseEnum(ErrorCodeEnum.CONFIG_ERROR, "order接口的值和list中的下标不一致"));
			}
		}

	}

	@Override
	public TmallProperty findByPrimaryKey(Number id)
	{
		int index = id.intValue() % daos.size();
		TmallPropertyDao dao = daos.get(index);
		TmallProperty property = dao.selectByPrimaryKey(id.intValue());
		return property;
	}

	@Override
	public List<TmallProperty> findByExample(Object example)
	{
		return null;
	}

	@Override
	public Integer deleteInBatchByExample(Object example)
	{
		return null;
	}

	@Override
	public Integer insertSelective(String tableName, TmallProperty t)
	{
		t.setTableName(tableName);
		Integer propertyId = t.getPropertyId();
		return daos.get(propertyId%daos.size()).insertSelective(t);
	}

	@Override
	public Integer updateSelectTive(String tableName, TmallProperty t, Object example)
	{
		return null;
	}

}
