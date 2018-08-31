/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午3:05:47
* 
*/
package com.tmall.common.service;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午3:05:47
 */
public interface INosqlService
{
	void set(String key, String value);

	void del(String key);

	void set(String key, String value, Integer interval);

	String get(String key);

}
