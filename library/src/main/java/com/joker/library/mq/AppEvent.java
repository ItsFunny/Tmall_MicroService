/**
*
* @author joker 
* @date 创建时间：2018年9月3日 下午6:07:59
* 
*/
package com.joker.library.mq;

import java.io.Serializable;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月3日 下午6:07:59
*/
@Data
public class AppEvent extends EventObject
{
	
	public AppEvent(String exchangeName,String eventType,Serializable data)
	{
		super("");
		this.uuid=UUID.randomUUID().toString();
		this.data=data;
		this.exchangeName=exchangeName;
		this.eventType=eventType;
		this.extProps=new HashMap<String, Object>();
	}
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月3日 下午6:08:13
	*/
	private static final long serialVersionUID = -9147729234199596095L;
	private long timestamp;
	private Serializable data;
	//exchangeName
	private String exchangeName;
	//routingKey
	private String eventType;
	//唯一的id
	private String uuid;
	
	//2018-09-07 14:39 add
	//微服务名称
	private String serverName;
	
	private Map<String, Object>extProps;
}
