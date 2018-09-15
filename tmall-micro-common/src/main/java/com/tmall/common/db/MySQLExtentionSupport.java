/**
*
* @author joker 
* @date 创建时间：2018年6月21日 上午11:27:25
* 
*/
package com.tmall.common.db;

import com.tmall.common.db.ExtentionInfo.DBInfo;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月21日 上午11:27:25
 */
public class MySQLExtentionSupport extends AbstractMySQLExtention
{

	@Override
	public ExtentionResult getTableNum(String tableName, ExtentionInfo info)
	{
		Integer count = this.tableInfo.getTotalCount(tableName);
		if (null != count)
		{
			Integer tableNumInteger = (int) (info.getId() % count);
			ExtentionResult result = new ExtentionResult();
			result.setTableNum(tableNumInteger);
			return result;
		}
		return null;
	}

	@Override
	protected void config(MySQLExtentionProperty property)
	{
		String value = property.getValue();
		String[] values = value.split(",");
		if (values.length <= 0)
		{
			throw new IllegalArgumentException("数据库配置参数错误,用逗号分隔");
		}
		for (String string : values)
		{
			String[] details = string.split(":");
			if (details.length <= 0)
			{
				throw new IllegalArgumentException("wrong config parameter");
			}
			this.tableInfo.pub(details[0], Integer.parseInt(details[1]));
		}
	}
	public static void main(String[] args)
	{
		ExtentionInfo extentionInfo=new ExtentionInfo();
		ExtentionResult result=new ExtentionResult();
		String string="category:2:1,2-user:1:2";
		String[] strings = string.split("-");
		for (String string2 : strings)
		{
			String[] details = string2.split(":");
			if(details.length!=3)
			{
				throw new RuntimeException("配置错误");
			}else {
				System.out.println("表名为:"+details[0]+",共有"+details[1]+"个库,共有"+details[2]+"张表");
			}
			String tableName=details[0];
			Integer dbCounts=Integer.parseInt(details[1]);
			DBInfo dbInfo=new ExtentionInfo.DBInfo();
			extentionInfo.setDbCounts(dbCounts);
			
			if(dbCounts>1)
			{
				
			}
		}
	}

}
