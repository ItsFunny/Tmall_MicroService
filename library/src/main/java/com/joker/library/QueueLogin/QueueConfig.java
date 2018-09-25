/**
*
* @author joker 
* @date 创建时间：2018年1月23日 下午12:43:59
* 
*/
package com.joker.library.QueueLogin;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
*
* @author joker 
* @date 创建时间：2018年1月23日 下午12:43:59
* 
*/
public class QueueConfig<T>
{
	public   static volatile boolean isBackThreadRun=false;
	private  static final QueueConfig<?>queueConfig=new QueueConfig<>();
	
	private QueueConfig()
	{
	}
	/**
	* @author joker 
	* @date 创建时间：2018年1月23日 下午12:47:02
	* 在线人数集合
	*/
	private   LinkedBlockingQueue<T> onLineUserQueue;
	
	/**
	* @author joker 
	* @date 创建时间：2018年1月23日 下午12:47:20
	* 等待登陆人数集合,存放的是唯一的key(自行选择,本项目采用用户的唯一标识,userId) 
	*/
	private   LinkedBlockingQueue<Object>waitUserKeyQueue;
	
	/**
	* @author joker 
	* @date 创建时间：2018年1月23日 下午12:58:34
	* 存放等待队列中人员信息,key为waitUserQueueKey对应
	*/
	private   ConcurrentMap<Object, T>waitUserMap;
	
	/**
	* 保存着最大人数时候的信息(日期)
	* @author joker 
	* @date 创建时间：2018年2月4日 下午2:24:45
	*/
	private Map<String, String> maxCountMap=new ConcurrentHashMap<String, String>();
	
	/**
	* @author joker 
	* @date 创建时间：2018年1月23日 下午12:45:22
	* 前端在线最大人数
	*/
	private Integer maxOnlineUserCount;
	
	/**
	* @author joker 
	* @date 创建时间：2018年1月23日 下午12:46:12
	* 等待队列最大人数
	*/
	private Integer maxWaitUserCount;
	
	public  static synchronized Integer getQueueSize(Collection<?>queue)
	{
		return queue.size();
	}
	public Integer getMaxOnlineUserCount()
	{
		return maxOnlineUserCount;
	}
	public void setMaxOnlineUserCount(Integer maxOnlineUserCount)
	{
		this.maxOnlineUserCount = maxOnlineUserCount;
	}
	public Integer getMaxWaitUserCount()
	{
		return maxWaitUserCount;
	}
	public void setMaxWaitUserCount(Integer maxWaitUserCount)
	{
		this.maxWaitUserCount = maxWaitUserCount;
	}
	public ConcurrentMap<Object, T> getWaitUserMap()
	{
		return waitUserMap;
	}
	public void setWaitUserMap(ConcurrentMap<Object, T> waitUserMap)
	{
		this.waitUserMap = waitUserMap;
	}
	public static <T>QueueConfig<T> generate(Integer maxOnlineUserCount, Integer maxWaitUserCount)
	{
		queueConfig.waitUserMap=new ConcurrentHashMap<>();
		queueConfig.maxOnlineUserCount=maxOnlineUserCount;
		queueConfig.maxWaitUserCount=maxWaitUserCount;
		queueConfig.waitUserKeyQueue=new LinkedBlockingQueue<>(maxWaitUserCount); 
		queueConfig.onLineUserQueue=new LinkedBlockingQueue<>(maxOnlineUserCount);
		return (QueueConfig<T>) queueConfig;
	}
	public static <T>QueueConfig<T>getQueueConfig()
	{
		return (QueueConfig<T>) queueConfig;
	}
	public static QueueConfig<?> getQueueconfig()
	{
		return queueConfig;
	}
	public Map<String, String> getMaxCountMap()
	{
		return maxCountMap;
	}
	public void setMaxCountMap(Map<String, String> maxCountMap)
	{
		this.maxCountMap = maxCountMap;
	}
	public LinkedBlockingQueue<T> getOnLineUserQueue()
	{
		return onLineUserQueue;
	}
	public void setOnLineUserQueue(LinkedBlockingQueue<T> onLineUserQueue)
	{
		this.onLineUserQueue = onLineUserQueue;
	}
	public static boolean isBackThreadRun()
	{
		return isBackThreadRun;
	}
	public LinkedBlockingQueue<Object> getWaitUserKeyQueue()
	{
		return waitUserKeyQueue;
	}
	public void setWaitUserKeyQueue(LinkedBlockingQueue<Object> waitUserKeyQueue)
	{
		this.waitUserKeyQueue = waitUserKeyQueue;
	}
	public static void setBackThreadRun(boolean isBackThreadRun)
	{
		QueueConfig.isBackThreadRun = isBackThreadRun;
	}


	
	
	
}
