/**
*
* @author joker 
* @date 创建时间：2018年9月4日 下午1:25:41
* 
*/
package com.joker.library.mq;

/**
* 
* @author joker 
* @date 创建时间：2018年9月4日 下午1:25:41
*/
public interface AppEventPublisher
{
	void publish(AppEvent event);
	
}
