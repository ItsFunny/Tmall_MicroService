///**
//*
//* @author joker 
//* @date 创建时间：2018年9月5日 下午7:48:14
//* 
//*/
//package com.joker.library.mq;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//
//import lombok.Data;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年9月5日 下午7:48:14
//*/
//@Data
//public abstract class AbstractConfirmCallback implements ConfirmCallback
//{
//	private String type;
//	
//	private AbstractConfirmCallback nextHandler;
//	
//	public AbstractConfirmCallback(String type)
//	{
//		this.type=type;
//	}
//	
//	
//	@Override
//	public void confirm(CorrelationData correlationData, boolean ack, String cause)
//	{
//		//这个依据业务而定,是谁的子类,好像无法在这里做到通用
//	}
//	
//	protected abstract void doConfirm(CorrelationData correlationData,boolean ack,String cause);
//	
//
//}
