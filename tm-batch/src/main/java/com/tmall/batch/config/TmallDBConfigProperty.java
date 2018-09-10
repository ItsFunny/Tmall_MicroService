/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午9:11:16
* 
*/
package com.tmall.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午9:11:16
 */
@Data
@ConfigurationProperties(prefix = "tmall.config.db")
public class TmallDBConfigProperty
{
	// username: root
	// password: 123456
	// url: jdbc:mysql://localhost/tmall_record?characterEncoding=utf-8&useSSL=false
	// driver-class-name: com.mysql.jdbc.Driver
	private String username;
	private String password;
	private String url;
	private String driverClassName;

}
