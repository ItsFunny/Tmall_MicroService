/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午12:47:25
* 
*/
package com.joker.library.mq;

import org.springframework.amqp.rabbit.support.CorrelationData;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午12:47:25
*/
@Data
public abstract class BaseCorrelationData extends CorrelationData
{
	private String type;
}
