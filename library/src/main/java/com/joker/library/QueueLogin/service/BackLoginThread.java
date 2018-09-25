/**
*
* @author joker 
* @date 创建时间：2018年2月3日 下午12:29:00
* 
*/
package com.joker.library.QueueLogin.service;

import com.joker.library.QueueLogin.QueueConfig;

/**
 * 
 * @author joker
 * @param <T>
 * @date 创建时间：2018年2月3日 下午12:29:00
 */
public class BackLoginThread<T> implements Runnable
{
	private QueueConfig<T> config;

	public BackLoginThread(QueueConfig<T> config)
	{
		this.config = config;
	}

	@Override
	public void run()
	{
		// 这里可以利用前置判断调用wait方法
		try
		{

			while (!Thread.currentThread().isInterrupted())
			{
				synchronized (this)
				{
					while (config.getWaitUserKeyQueue().size() == 0)
					{
						wait();
					}
				}
				if (QueueConfig.isBackThreadRun)
				{
					synchronized (this)
					{
						try
						{
							Object obj = config.getWaitUserKeyQueue().peek();
							if (obj != null)
							{
								System.out.println("后台线程取到了userDTO:" + obj);
								T t = config.getWaitUserMap().get(obj);
								if (t != null)
								{
									config.getOnLineUserQueue().put(t);
									System.out.println(t + "在后台线程中登陆了");
									config.getWaitUserKeyQueue().remove(obj);
								}

							}
						} catch (InterruptedException e)
						{
							// error way todo so
							e.printStackTrace();
						}
					}
				}

			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}

	}
}
