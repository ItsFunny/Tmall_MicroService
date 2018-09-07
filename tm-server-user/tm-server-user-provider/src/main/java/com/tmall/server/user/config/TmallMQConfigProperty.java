/**
*
* @author joker 
* @date 创建时间：2018年9月3日 下午4:55:25
* 
*/
package com.tmall.server.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 下午4:55:25
 */
@Data
@ConfigurationProperties(prefix = "tmall.config.mq")
public class TmallMQConfigProperty
{
	private String amqpAddrss;
	private Integer amqpPort;
	private String amqpUsername;
	private String amqpPassword;
	// db
}
