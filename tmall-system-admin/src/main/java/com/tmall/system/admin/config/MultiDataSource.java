/**
*
* @author joker 
* @date 创建时间：2018年8月27日 上午9:06:54
* 
*/
package com.tmall.system.admin.config;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* 
* @author joker 
* @date 创建时间：2018年8月27日 上午9:06:54
*/
public class MultiDataSource extends AbstractRoutingDataSource
{
	
	private final ThreadLocal<String>dataSources=new ThreadLocal<>();
	
	public void set(String dataSourceBeanName)
	{
		this.dataSources.set(dataSourceBeanName);
	}
	
	@Override
	protected Object determineCurrentLookupKey()
	{
		return this.dataSources.get();
	}


}
