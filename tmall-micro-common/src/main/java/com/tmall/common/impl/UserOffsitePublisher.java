///**
//*
//* @author joker 
//* @date 创建时间：2018年5月15日 下午10:31:17
//* 
//*/
//package com.tmall.common.impl;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.tmall.common.event.AbstractAppPublish;
//import com.tmall.common.event.AppEvent;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年5月15日 下午10:31:17
// */
//public class UserOffsitePublisher extends AbstractAppPublish
//{
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	public void publish(AppEvent event)
//	{
//		rabbitTemplate.convertAndSend(this.getExchangeName(), this.getRoutingKey(), event);
//	}
//
//	@Override
//	public void publish(String exchangeName, String routingKey, AppEvent event)
//	{
//		rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
//	}
//
//}
