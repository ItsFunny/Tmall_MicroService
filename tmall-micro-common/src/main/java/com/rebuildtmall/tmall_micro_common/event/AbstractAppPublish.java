/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午9:16:13
* 
*/
package com.rebuildtmall.tmall_micro_common.event;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午9:16:13
 */
public abstract class AbstractAppPublish implements AppPublish
{
	private String exchangeName;
	private String routingKey;
	private String defaultRoutingKey="";

	private String defalutExchangeName="";

	public void defaultPublish(AppEvent event)
	{
		publish(defalutExchangeName, defaultRoutingKey, event);
	}

	public String getDefaultRoutingKey()
	{
		return defaultRoutingKey;
	}

	public void setDefaultRoutingKey(String defaultRoutingKey)
	{
		this.defaultRoutingKey = defaultRoutingKey;
	}

	public String getDefalutExchangeName()
	{
		return defalutExchangeName;
	}

	public void setDefalutExchangeName(String defalutExchangeName)
	{
		this.defalutExchangeName = defalutExchangeName;
	}

	public String getExchangeName()
	{
		return exchangeName;
	}

	public void setExchangeName(String exchangeName)
	{
		this.exchangeName = exchangeName;
	}

	public String getRoutingKey()
	{
		return routingKey;
	}

	public void setRoutingKey(String routingKey)
	{
		this.routingKey = routingKey;
	}

}
