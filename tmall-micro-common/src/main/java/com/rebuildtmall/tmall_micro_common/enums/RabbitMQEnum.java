/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午8:44:56
* 
*/
package com.rebuildtmall.tmall_micro_common.enums;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午8:44:56
 */
public enum RabbitMQEnum
{
	USER_ABNORMAL_OFFSITE("USER_ABNORMAL", "USER_OFFSITE", "OFFSITE"),

	;
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
