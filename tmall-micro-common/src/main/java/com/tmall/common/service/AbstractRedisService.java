/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午3:02:18
* 
*/
package com.tmall.common.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午3:02:18
 */
public abstract class AbstractRedisService implements INosqlService
{
	protected JedisPool jedisPool;

	public abstract void config(JedisPool jedisPool);
	
	@Override
	public void set(String key, String value)
	{
		Jedis jedis = jedisPool.getResource();
		try
		{
			jedis.set(key, value);
		} finally
		{
			if (null != jedis)
			{
				jedis.close();
			}
		}
	}

	@Override
	public void del(String key)
	{
		Jedis jedis = jedisPool.getResource();
		try
		{
			jedis.del(key);
		} finally
		{
			if (null != jedis)
			{
				jedis.close();
			}
		}
	}

	@Override
	public void set(String key, String value, Integer interval)
	{
		Jedis jedis = jedisPool.getResource();
		try
		{
			jedis.set(key, value);
			jedis.expire(key, interval);
		} finally
		{
			if (null != jedis)
			{
				jedis.close();
			}
		}
	}

	@Override
	public String get(String key)
	{
		Jedis jedis = jedisPool.getResource();
		try
		{
			String json = jedis.get(key);
			return json;
		} finally
		{
			if (null != jedis)
			{
				jedis.close();
			}
		}
	}

}
