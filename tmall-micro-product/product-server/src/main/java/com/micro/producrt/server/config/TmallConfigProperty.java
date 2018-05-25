/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午10:28:35
* 
*/
package com.micro.producrt.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* 
*/
@ConfigurationProperties(prefix = "tmall.datasource")
public class TmallConfigProperty
{
	private String username;
	private String password;
	private String driverClassName;
	private String url;

	private Integer wokerId = 0;
	private Integer dataCenterId = 0;



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


}
