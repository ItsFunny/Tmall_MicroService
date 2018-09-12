///**
//*
//* @author joker 
//* @date 创建时间：2018年9月9日 上午11:32:48
//* 
//*/
//package com.tmall.common.aop;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.joker.library.dto.ResultDTO;
//import com.joker.library.utils.ResultUtils;
//import com.tmall.common.annotation.RabbitMQTransaction;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.common.exception.TmallBussinessException;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年9月9日 上午11:32:48
// */
//@Aspect
//@Component
//public class TmallTransactioonAOP
//{
//	
//	
//	
//	
//	
//	@Pointcut("@annotation(com.tmall.common.annotation.RabbitMQTransaction)")
//	public void dateTransactionWithMessage()
//	{
//	}
//
//	@Around("dateTransactionWithMessage()")
//	public ResultDTO<String> confirmDateIsolutation(ProceedingJoinPoint proceedingJoinPoint,
//			RabbitMQTransaction annotation)
//	{
//		String exchangeName = annotation.exchangeName();
//		String routingKey = annotation.routingKey();
//		if (StringUtils.isEmpty(exchangeName) || StringUtils.isEmpty(routingKey))
//		{
//			throw new TmallBussinessException(
//					ErrorCodeEnum.parseEnum(ErrorCodeEnum.MISSING_ARGUMENT, "消息发送的exchangeName和routingKey不能为空"));
//		}
//		
//
//		return ResultUtils.sucess();
//	}
//
//}
