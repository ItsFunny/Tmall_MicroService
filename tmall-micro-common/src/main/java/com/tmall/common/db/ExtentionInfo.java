/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午9:26:28
* 
*/
package com.tmall.common.db;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月4日 上午9:26:28
 */
public class ExtentionInfo
{
	private Long id;

	private Integer sqlTableTotalCount;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Integer getSqlTableTotalCount()
	{
		return sqlTableTotalCount;
	}

	public void setSqlTableTotalCount(Integer sqlTableTotalCount)
	{
		this.sqlTableTotalCount = sqlTableTotalCount;
	}

}
