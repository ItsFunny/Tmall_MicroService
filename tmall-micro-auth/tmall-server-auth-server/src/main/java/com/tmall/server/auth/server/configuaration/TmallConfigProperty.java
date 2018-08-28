/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午10:28:35
* 
*/
package com.tmall.server.auth.server.configuaration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* 
*/
@ConfigurationProperties(prefix = "tmall.config")
public class TmallConfigProperty
{
	private String username;
	private String password;
	private String driverClassName;
	private String url;

	private Integer wokerId = 0;
	private Integer dataCenterId = 0;

	private Boolean amqpEnabled;
	
	private String emailAccount;
	private String emailPwd;
	private String emailHost;
	
	//mysql 的扩展
	private String mysqlExtention;
	
	//rabbitmq
	private String amqpHost;
	private String amqpUsername;
	private String amqpPassword;
	
	
	
	
	
	
	


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDriverClassName()
	{
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName)
	{
		this.driverClassName = driverClassName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getWokerId()
	{
		return wokerId;
	}

	public void setWokerId(Integer wokerId)
	{
		this.wokerId = wokerId;
	}

	public Integer getDataCenterId()
	{
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId)
	{
		this.dataCenterId = dataCenterId;
	}

	public Boolean getAmqpEnabled()
	{
		return amqpEnabled;
	}

	public void setAmqpEnabled(Boolean amqpEnabled)
	{
		this.amqpEnabled = amqpEnabled;
	}

	public String getEmailAccount()
	{
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount)
	{
		this.emailAccount = emailAccount;
	}

	public String getEmailPwd()
	{
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd)
	{
		this.emailPwd = emailPwd;
	}

	public String getEmailHost()
	{
		return emailHost;
	}

	public void setEmailHost(String emailHost)
	{
		this.emailHost = emailHost;
	}

	public String getMysqlExtention()
	{
		return mysqlExtention;
	}

	public void setMysqlExtention(String mysqlExtention)
	{
		this.mysqlExtention = mysqlExtention;
	}

	public String getAmqpHost()
	{
		return amqpHost;
	}

	public void setAmqpHost(String amqpHost)
	{
		this.amqpHost = amqpHost;
	}

	public String getAmqpUsername()
	{
		return amqpUsername;
	}

	public void setAmqpUsername(String amqpUsername)
	{
		this.amqpUsername = amqpUsername;
	}

	public String getAmqpPassword()
	{
		return amqpPassword;
	}

	public void setAmqpPassword(String amqpPassword)
	{
		this.amqpPassword = amqpPassword;
	}


}
