/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午3:14:39
* 
*/
package com.joker.library.sqlextention;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午3:14:39
*/
@ConfigurationProperties(prefix="sql.extention")
public class SQLExtentionConfigProperty
{
	//总共有几张表进行了分库分片处理
	private Integer totalTableCounts;
	
	//前缀名称的集合
	private String tablePrefixNames;
	
	
	/*
	 * 具体的配置
	 * 多个表用-分隔,单表的结构为:
	 * 分库数目:facadedDao的bean名称:这个库A下表的数量=表的名称1,表的名称2;这个库B下表的数量=表的名称1,表的名称2..
	 */
	private String detailConfigStr;


	public Integer getTotalTableCounts()
	{
		return totalTableCounts;
	}


	public void setTotalTableCounts(Integer totalTableCounts)
	{
		this.totalTableCounts = totalTableCounts;
	}


	public String getTablePrefixNames()
	{
		return tablePrefixNames;
	}


	public void setTablePrefixNames(String tablePrefixNames)
	{
		this.tablePrefixNames = tablePrefixNames;
	}


	public String getDetailConfigStr()
	{
		return detailConfigStr;
	}


	public void setDetailConfigStr(String detailConfigStr)
	{
		this.detailConfigStr = detailConfigStr;
	}
	
	

}
