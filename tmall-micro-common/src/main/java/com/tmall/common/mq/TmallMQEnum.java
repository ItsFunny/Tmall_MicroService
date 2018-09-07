/**
*
* @author joker 
* @date 创建时间：2018年9月3日 下午5:31:42
* 
*/
package com.tmall.common.mq;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 下午5:31:42
 */
public enum TmallMQEnum
{
	USER_RECORD("tmall_record", "record", "user"),;
	private String exchangeName;
	private String queueName;
	private String routinKey;

	private TmallMQEnum(String exchangeName, String queueName, String routinKey)
	{
		this.exchangeName = exchangeName;
		this.queueName = queueName;
		this.routinKey = routinKey;
	}


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

	public String getRoutinKey()
	{
		return routinKey;
	}

	public void setRoutinKey(String routinKey)
	{
		this.routinKey = routinKey;
	}

}
