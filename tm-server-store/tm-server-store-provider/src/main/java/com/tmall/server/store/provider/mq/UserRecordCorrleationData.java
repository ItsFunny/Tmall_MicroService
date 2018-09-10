/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午10:22:16
* 
*/
package com.tmall.server.store.provider.mq;

import org.springframework.amqp.rabbit.support.CorrelationData;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 上午10:22:16
*/
@Data
public class UserRecordCorrleationData extends CorrelationData
{
	//json data
	private String data;
	

}
