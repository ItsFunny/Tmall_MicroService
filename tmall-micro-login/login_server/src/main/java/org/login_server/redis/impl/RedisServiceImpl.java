/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午10:46:30
* 
*/
package org.login_server.redis.impl;

import org.login_server.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午10:46:30
 */
@Component
public class RedisServiceImpl implements IRedisService
{

	@Autowired
	private JedisPool jedisPool;

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
