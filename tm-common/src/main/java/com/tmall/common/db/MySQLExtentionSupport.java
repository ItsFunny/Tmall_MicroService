/**
*
* @author joker 
* @date 创建时间：2018年6月21日 上午11:27:25
* 
*/
package com.tmall.common.db;

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
			throw new IllegalArgumentException("wrong config parameter");
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

}
