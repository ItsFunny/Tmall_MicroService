/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午10:45:10
* 
*/
package org.login_server.redis;

/**
* 
* @author joker 
* @date 创建时间：2018年5月14日 上午10:45:10
*/
public interface IRedisService
{
	void set(String key,String value);
	
	void del(String key);
	
	void set(String key,String value,Integer interval);
	
	String get(String key);
	
	
}
