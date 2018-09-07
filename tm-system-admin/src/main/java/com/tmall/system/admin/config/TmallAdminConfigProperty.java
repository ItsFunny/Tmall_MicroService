/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月28日 下午9:52:07
* 
*/
package com.tmall.system.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月28日 下午9:52:07
 */
@ConfigurationProperties(prefix = "tmall.config.admin")
public class TmallAdminConfigProperty
{
	private String authDetailUrl;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	private String dbDriverClassName;

	private boolean amqpEnabled;

	public String getAuthDetailUrl()
	{
		return authDetailUrl;
	}

	public void setAuthDetailUrl(String authDetailUrl)
	{
		this.authDetailUrl = authDetailUrl;
	}

	public String getDbUrl()
	{
		return dbUrl;
	}

	public void setDbUrl(String dbUrl)
	{
		this.dbUrl = dbUrl;
	}

	public String getDbUsername()
	{
		return dbUsername;
	}

	public void setDbUsername(String dbUsername)
	{
		this.dbUsername = dbUsername;
	}

	public String getDbPassword()
	{
		return dbPassword;
	}

	public void setDbPassword(String dbPassword)
	{
		this.dbPassword = dbPassword;
	}

	public String getDbDriverClassName()
	{
		return dbDriverClassName;
	}

	public void setDbDriverClassName(String dbDriverClassName)
	{
		this.dbDriverClassName = dbDriverClassName;
	}

	public boolean isAmqpEnabled()
	{
		return amqpEnabled;
	}

	public void setAmqpEnabled(boolean amqpEnabled)
	{
		this.amqpEnabled = amqpEnabled;
	}

}
