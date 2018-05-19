/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午3:03:00
* 
*/
package com.tmall.common.service.impl;

import com.tmall.common.service.AbstractRedisService;

import redis.clients.jedis.JedisPool;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午3:03:00
 */
public class RedisServiceImpl extends AbstractRedisService
{

	@Override
	public void config(JedisPool jedisPool)
	{
		super.jedisPool=jedisPool;
	}

	
}
