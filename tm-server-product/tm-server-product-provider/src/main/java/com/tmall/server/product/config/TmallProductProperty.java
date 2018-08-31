/**
*
* @author joker 
* @date 创建时间：2018年8月27日 上午9:14:54
* 
*/
package com.tmall.server.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月27日 上午9:14:54
 */
@ConfigurationProperties(prefix = "tmall.config")
public class TmallProductProperty
{
	private String anotherUrl;

	private String anotherUsername;
	private String anotherPassword;

	public String getAnotherUrl()
	{
		return anotherUrl;
	}

	public void setAnotherUrl(String anotherUrl)
	{
		this.anotherUrl = anotherUrl;
	}

	public String getAnotherUsername()
	{
		return anotherUsername;
	}

	public void setAnotherUsername(String anotherUsername)
	{
		this.anotherUsername = anotherUsername;
	}

	public String getAnotherPassword()
	{
		return anotherPassword;
	}

	public void setAnotherPassword(String anotherPassword)
	{
		this.anotherPassword = anotherPassword;
	}

}
