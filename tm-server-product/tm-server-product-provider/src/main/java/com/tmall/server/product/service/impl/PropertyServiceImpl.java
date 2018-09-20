/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:33
* 
*/
package com.tmall.server.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.other.ISQLExtentionBaseCRUDService;
import com.tmall.common.other.SQLExtentionHolder;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.service.IPropertyService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:33
*/
@Service
public class PropertyServiceImpl implements IPropertyService
{
	@Lazy
	private InnerPropertySQLExtentionCRUDServiceImpl serviceImpl=this.new InnerPropertySQLExtentionCRUDServiceImpl();
	
	@Autowired
	private SQLExtentionHolder holder;
	
	private class InnerPropertySQLExtentionCRUDServiceImpl implements ISQLExtentionBaseCRUDService<TmallProperty>
	{

		@Override
		public Integer insert(String tableName, Long hashCodeOrId, TmallProperty t)
		{
			holder.getBaseDao(tableName, hashCodeOrId);
			return null;
		}

		@Override
		public Integer update(String tableName, Number id, TmallProperty t, Object example)
		{
			return null;
		}

		@Override
		public TmallProperty findByPrimaryKey(String tableName, Number id)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<TmallProperty> findByExample(Object object)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Integer deleteInBatchByExample(Object example)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	@Override
	public ResultDTO<?> addPropertyAndValue(UserRecordAspectWrapper<PropertyDTO> wrapper)
	{
		return null;
	}
	

}
