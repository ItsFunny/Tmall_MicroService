/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午9:04:35
* 
*/
package com.joker.library.mq;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午9:04:35
 */
public interface AppEventConsumer
{
	int consume(AppEvent event);
}
