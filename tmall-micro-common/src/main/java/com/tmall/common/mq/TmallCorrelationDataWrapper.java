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
	
	
}
