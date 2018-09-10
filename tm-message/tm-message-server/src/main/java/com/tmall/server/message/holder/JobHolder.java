/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午4:50:04
* 
*/
package com.tmall.server.message.holder;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.ConcurrentHashMultiset;
import com.joker.library.mq.AppEvent;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 会将这里的代码采用redis的方式,而不是基于内存.内存要爆炸的
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午4:50:04
 */
@Data
@Slf4j
public class JobHolder
{
	private LinkedBlockingQueue<JobWrapper> eventsQueue;

	private ReentrantLock lock = new ReentrantLock();

	private static JobHolder jobHolder = new JobHolder();

	private JobHolder()
	{
		this.eventsQueue = new LinkedBlockingQueue<>();
	}

	public static JobHolder getSingleTonJobHolder()
	{
		return jobHolder;
	}

	public void delete(String id)
	{
		try
		{
			lock.lock();
			Iterator<JobWrapper> iterator = eventsQueue.iterator();
			while (iterator.hasNext())
			{
				JobWrapper wrapper = iterator.next();
				if (wrapper.getEvent().getUuid().equals(id))
				{
					iterator.remove();
				}
			}
		} finally
		{
			lock.unlock();
		}
	}

	public JobWrapper get(String id)
	{
		for (JobWrapper jobWrapper : eventsQueue)
		{
			if (jobWrapper.getEvent().getUuid().equals(id))
			{
				return jobWrapper;
			}
		}
		return null;
	}

	public void addJob(AppEvent event)
	{
		JobWrapper jobWrapper = new JobWrapper();
		jobWrapper.setEvent(event);
		jobWrapper.setStatus(JobWrapper.NEW);
		try
		{
			// 5s
			boolean offer = this.eventsQueue.offer(jobWrapper, 5, TimeUnit.SECONDS);
			if (!offer)
			{
				retry(jobWrapper);
			}
		} catch (InterruptedException e)
		{
			retry(jobWrapper);
		}
	}

	public void retry(JobWrapper wrapper)
	{
		int i = 0;
		boolean res = false;
		while (i++ < 3)
		{
			log.info("[任务调度器]任务:{},第{}次尝试重新加入任务中", wrapper, i);
			try
			{
				res = this.eventsQueue.offer(wrapper, 3, TimeUnit.SECONDS);
				if (res)
				{
					return;
				}
			} catch (InterruptedException e1)
			{
				log.info("[任务调度器]第{}次尝试重新加入任务中失败", i, e1);
			}
		}
		if (!res)
		{
			throw new TmallBussinessException(ErrorCodeEnum.MQ_ADD_JOB_TO_QUEUE_FAIL);
		}
	}
}
