/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午9:00:00
* 
*/
package com.tmall.server.product.dao.sqlextention;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.dao.TmallCategoryDao;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 下午9:00:00
*/
@Component(value="categoryFacadedDaoImpl")
public class CategoryFacadedDaoImpl implements ISQLExtentionBaseCRUDDao<TmallCategory>
{
	@Autowired
	private List<TmallCategoryDao>daos;
	
	@PostConstruct
	public void checkConfig()
	{
		for(int i=0;i<daos.size();i++)
		{
			Annotation[] annotations = daos.get(i).getClass().getAnnotations();
			Order order = daos.get(i).getClass().getAnnotation(Order.class);
			if(null==order)
			{
				throw new RuntimeException("配置有误");
			}
			int index = order.value();
			if(index!=i)
			{
				throw new RuntimeException("配置有误");
			}
		}
	
	}

	@Override
	public Integer insertSelective(String tableName, TmallCategory t)
	{
		
		return null;
	}

	@Override
	public Integer updateSelectTive(String tableName, TmallCategory t, Object example)
	{
		return null;
	}

	@Override
	public TmallCategory findByPrimaryKey(Number id)
	{
		return null;
	}

	@Override
	public List<TmallCategory> findByExample(Object example)
	{
		return null;
	}

	@Override
	public Integer deleteInBatchByExample(Object example)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
