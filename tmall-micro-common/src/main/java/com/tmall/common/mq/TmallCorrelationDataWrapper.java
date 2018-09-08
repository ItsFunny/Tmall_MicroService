/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午7:52:35
* 
*/
package com.tmall.common.mq;


import org.springframework.amqp.rabbit.support.CorrelationData;

import com.joker.library.mq.AppEvent;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 下午7:52:35
*/
@Data
public class TmallCorrelationDataWrapper extends CorrelationData
{
	private String type;
	
	private AppEvent callBackData;
	
	//2018-09-07 17:42 add 为什么添加了,为了防止因为超时时间太短,而服务间调用消耗时间太长的问题,所以在这里定义一个serverName,用来
	//获取具体的服务地址,从而可以获取消息状态
	private String serverName;
	
	
}
