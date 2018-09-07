/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午11:04:15
* 
*/
package com.tmall.server.user.mq.consumers;

import com.joker.library.mq.AppEvent;
import com.joker.library.mq.AppEventConsumer;

/**
* 
* @author joker 
* @date 创建时间：2018年9月4日 上午11:04:15
*/
public class UserRecordConsumer implements AppEventConsumer
{
	@Override
	public int consume(AppEvent event)
	{
		return 0;
	}
}
