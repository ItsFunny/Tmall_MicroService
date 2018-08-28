/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午9:49:23
* 
*/
package com.tmall.common.db.factory;

import org.springframework.beans.factory.FactoryBean;

import com.tmall.common.db.MySQLExtention;

/**
* 
* @author joker 
* @date 创建时间：2018年6月4日 上午9:49:23
*/
public class MySQLExtentionFactoryBean implements FactoryBean<MySQLExtention>
{
	private MySQLExtention extention;
	
	
	
	
	@Override
	public MySQLExtention getObject() throws Exception
	{
		if(null==extention)
		{
			throw new RuntimeException("extention must  not be null");
		}
		return extention;
	}

	@Override
	public Class<?> getObjectType()
	{
		return MySQLExtention.class;
	}

	public MySQLExtention getExtention()
	{
		return extention;
	}

	public void setExtention(MySQLExtention extention)
	{
		this.extention = extention;
	}

}
