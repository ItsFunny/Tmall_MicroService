package com.joker.library.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ralph Jiang
 * 
 */
public class IdWorkerServiceTwitter implements IdWorkerService
{

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;

	private long twepoch = 1440342714955L; // code publish date: 2015-08-23
											// 23:11:54

	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private long sequenceBits = 12L;

	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long lastTimestamp = -1L;

	public IdWorkerServiceTwitter(long workerId, long datacenterId)
	{
		// sanity check for workerId
		if (workerId > maxWorkerId || workerId < 0)
		{
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0)
		{
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		logger.debug(
				"worker starting. timestamp left shift {}, datacenter id bits {}, worker id bits {}, sequence bits {}, workerid {}",
				timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
	}

	public synchronized long nextId()
	{
		long timestamp = timeGen();

		if (timestamp < lastTimestamp)
		{
			logger.debug("clock is moving backwards.  Rejecting requests until {}.", lastTimestamp);
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		if (lastTimestamp == timestamp)
		{
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0)
			{
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else
		{
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	@Override
	public Collection<Long> nextIds(int num)
	{
		List<Long> result = new LinkedList<Long>();
		for (int i = 0; i < num; i++)
		{
			result.add(nextId());
		}
		return result;
	}
//	public static void main(String[] args)
//	{
//		IdWorkerServiceTwitter idWorkerServiceTwitter=new IdWorkerServiceTwitter(0	,1);
//		Collection<Long> nextId = idWorkerServiceTwitter.nextIds(5);
//		for (Long long1 : nextId)
//		{
//			System.out.println(long1);
//		}
//		System.out.println(nextId);
//	}
	protected long tilNextMillis(long lastTimestamp)
	{
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp)
		{
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen()
	{
		return System.currentTimeMillis();
	}
//	public static void main(String[] args)
//	{
//		IdWorkerServiceTwitter twitter=new IdWorkerServiceTwitter(0L,1L);
//		for(int i=0;i<1000;i++)
//		{
//			System.out.println(twitter.nextId());
//		}
//	}
}
