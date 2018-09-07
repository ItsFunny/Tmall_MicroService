/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午11:10:46
* 
*/
package com.tmall.server.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月4日 上午11:10:46
*/
@Data
@ConfigurationProperties(prefix="tmall.config.db")
public class TmallUserDBConfigProperty
{
	private String db1Url;
	private String db1Username;
	private String db1Passsword;
	private String db1DriverClassName;
	
	private String db2Url;
	private String db2Username;
	private String db2Password;
	private String db2DriverClassName;
	
	
}
