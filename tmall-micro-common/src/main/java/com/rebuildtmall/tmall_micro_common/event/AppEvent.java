/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午9:10:32
* 
*/
package com.rebuildtmall.tmall_micro_common.event;

import java.io.Serializable;
import java.util.EventObject;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午9:10:32
 */
public class AppEvent extends EventObject
{

	private long beginTimeMills;
	private String routingKey;
	private Serializable data;

	public AppEvent()
	{
		super("");
	}

	public AppEvent(Serializable data, String routingKey)
	{
		super(data);
		this.data = data;
		this.routingKey = routingKey;
		this.beginTimeMills = System.currentTimeMillis();
	}

	public AppEvent(Object source)
	{
		super(source);
	}

	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年5月15日 下午9:13:47
	 */
	private static final long serialVersionUID = 7761669551537571664L;

	@SuppressWarnings("unchecked")
	public<T> T getData()
	{
		return (T) data;
	}

	public void setData(Serializable data)
	{
		this.data = data;
	}

	public long getBeginTimeMills()
	{
		return beginTimeMills;
	}

	public void setBeginTimeMills(long beginTimeMills)
	{
		this.beginTimeMills = beginTimeMills;
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
