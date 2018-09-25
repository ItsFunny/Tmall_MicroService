/**
*
* @author joker 
* @date 创建时间：2018年2月2日 下午4:13:45
* 
*/
package com.joker.library.QueueLogin;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.joker.library.QueueLogin.Exception.QueueLoginRangeException;
import com.joker.library.QueueLogin.model.QueueLoginResultInfo;
import com.joker.library.QueueLogin.service.QueueLoginService;
import com.joker.library.utils.CollectionUtils;

/**
* 
* @author joker 
 * @param <T>
* @date 创建时间：2018年2月2日 下午4:13:45
*/
public abstract class AbstractLoginServiceImpl<T> implements QueueLoginService
{
	
	protected QueueConfig<T> config;
	protected Integer maxOnlineUserCount;
	protected Integer maxWaitUserCount;
	

	public  QueueLoginResultInfo enter(Object key,T t) throws QueueLoginRangeException
	{
		/*
		 * 可能的情况:
		 * 	1.用户正常登陆
		 * 	2.用户已经登陆后
		 * 		2.1 用户重复登陆自己的账户
		 * 		2.2 如果使用sessionId:用户在相同浏览器登陆不同人的账户
		 */
		QueueLoginResultInfo resultInfo=null;
		LinkedBlockingQueue<T> onLineUserQueue = config.getOnLineUserQueue();
		LinkedBlockingQueue<Object> waitUserKeyQueue = config.getWaitUserKeyQueue();
		if(!onLineUserQueue.contains(t))
		{
			if(config.getWaitUserKeyQueue().isEmpty()&&config.getWaitUserKeyQueue().size()==0)
			{
				resultInfo = enterOnLinueUserQueue(key,t);
			}else {
				resultInfo = enterWaitLoginQueue(key,t);
			}
		}else {
			resultInfo=null;
		}
		return resultInfo;
	}
	public  QueueLoginResultInfo enterOnLinueUserQueue(Object key,T t) throws QueueLoginRangeException
	{
		Integer size = QueueConfig.getQueueSize(config.getOnLineUserQueue());
		if (size < config.getMaxOnlineUserCount())
		{
			boolean offer = config.getOnLineUserQueue().offer(t);
			if (offer)
			{
				System.out.println(t + "登陆成功");
				return QueueLoginResultInfo.sucess();
			} else
			{
				System.out.println("登陆失败,调入等待队列");
				return enterWaitLoginQueue(key,t);
			}
		} else
		{
			return enterWaitLoginQueue(key,t);
		}
	}
	public QueueLoginResultInfo enterWaitLoginQueue(Object key,T t) throws QueueLoginRangeException
	{
		if (config.getWaitUserKeyQueue().contains(key))
		{
			System.out.println("用户已在等待队列中,删除之前的key");
			config.getWaitUserKeyQueue().remove(key);
		}
		Integer size = QueueConfig.getQueueSize(config.getWaitUserKeyQueue());
		if (size < config.getMaxWaitUserCount())
		{
			boolean offer = config.getWaitUserKeyQueue().offer(key);
			try
			{
				if (offer)
				{
					QueueConfig.isBackThreadRun = true;
					System.out.println("进入排队等待登陆队列");
					config.getWaitUserMap().put(key, t);
//					Class<? extends Object> class1 = t.getClass();
//					@SuppressWarnings("unchecked")
//					T[] arr = (T[]) Array.newInstance(class1, QueueConfig.getQueueSize(config.getWaitUserKeyQueue()));
//					T[] array = config.getWaitUserKeyQueue().toArray(arr);
//					List<T> list = Arrays.asList(array);
//					int index = list.indexOf(t);
					List<?> list2 = CollectionUtils.collection2List(config.getWaitUserKeyQueue(), Object.class);
					int index = list2.indexOf(key);
					return QueueLoginResultInfo.inLine(index);
				} else
				{
					throw new QueueLoginRangeException("等待登陆人数过多");
				}
			} finally
			{
			}
		} else
		{
			throw new QueueLoginRangeException("等待登陆人数过多");
		}
	}

	protected Integer getMaxOnlineUserCount()
	{
		return maxOnlineUserCount;
	}

	protected void setMaxOnlineUserCount(Integer maxOnlineUserCount)
	{
		this.maxOnlineUserCount = maxOnlineUserCount;
	}

	protected Integer getMaxWaitUserCount()
	{
		return maxWaitUserCount;
	}

	protected void setMaxWaitUserCount(Integer maxWaitUserCount)
	{
		this.maxWaitUserCount = maxWaitUserCount;
	}
}
