/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午9:57:00
* 
*/
package com.tmall.common.db;

/**
* 
* @author joker 
* @date 创建时间：2018年6月4日 上午9:57:00
*/
public abstract class AbstractMySQLExtention implements  MySQLExtention
{
	protected TableInfo tableInfo;
	
	protected abstract void config(MySQLExtentionProperty property);
	
	
	protected void init()
	{
		this.tableInfo=TableInfo.getTableInfo();
	}
	public void config(String value)
	{
		init();
		MySQLExtentionProperty property=new MySQLExtentionProperty();
		property.setValue(value);
		config(property);
	}


	@Override
	public ExtentionResult getTableNum(String tableName, Long id)
	{
		ExtentionInfo info=new ExtentionInfo();
		info.setId(id);
		return getTableNum(tableName, info);
	}
	
}
