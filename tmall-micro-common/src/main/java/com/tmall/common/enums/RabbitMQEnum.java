/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午8:44:56
* 
*/
package com.tmall.common.enums;

import com.tmall.common.constants.RabbitMQExchangeNameConstant;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午8:44:56
 */
public enum RabbitMQEnum
{
	// 用户异常操作的消息
	USER_ABNORMAL_OFFSITE("USER_ABNORMAL", "USER_OFFSITE", "OFFSITE"),

	// 因后台管理而使得用户状态发生改变->强制下线
	USER_OFFLINE_BY_FORCE(RabbitMQExchangeNameConstant.USER_STATUS_CHANGE, "CHANGE_BY_MANAGER", "OFFLINE"),

	// 因后台管理而使得用户状态发生改变->单单只是状态改变,如添加了权限等
	USER_STATUS_CHANGE_BY_MANAGER(RabbitMQExchangeNameConstant.USER_STATUS_CHANGE, "CHANGE_BY_MANAGER", "AUTH_CHANGE"),;
	private String exchangeName;
	private String queueName;
	private String routingKey;
	private String consumeReturnQueueName;

	public String getExchangeName()
	{
		return exchangeName;
	}

	public void setExchangeName(String exchangeName)
	{
		this.exchangeName = exchangeName;
	}

	public String getQueueName()
	{
		return queueName;
	}

	public void setQueueName(String queueName)
	{
		this.queueName = queueName;
	}

	public String getRoutingKey()
	{
		return routingKey;
	}

	public void setRoutingKey(String routingKey)
	{
		this.routingKey = routingKey;
	}

	private RabbitMQEnum(String exchangeName, String queueName, String routingKey)
	{
		this.exchangeName = exchangeName;
		this.queueName = queueName;
		this.routingKey = routingKey;
		this.consumeReturnQueueName = queueName;
	}

	public String getConsumeReturnQueueName()
	{
		return consumeReturnQueueName;
	}

	public void setConsumeReturnQueueName(String consumeReturnQueueName)
	{
		this.consumeReturnQueueName = consumeReturnQueueName;
	}

}
