/**
*
* @author joker 
* @date 创建时间：2018年5月16日 上午9:49:18
* 
*/
package com.tmall.common.event;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月16日 上午9:49:18
 */
public interface AppEventPublisher
{
	  void publish(AppEvent event);

//	  void publish(String routingKey, AppEvent event);
//	  
//	  void publish(String exchangeName,String routingKey,AppEvent event);
}
