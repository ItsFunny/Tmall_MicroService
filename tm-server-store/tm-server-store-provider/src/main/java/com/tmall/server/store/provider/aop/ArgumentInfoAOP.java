///**
//*
//* @author joker 
//* @date 创建时间：2018年9月6日 下午1:50:53
//* 
//*/
//package com.tmall.server.store.provider.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//
//import com.tmall.common.annotation.ArgumentCheck;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年9月6日 下午1:50:53
//*/
//@Aspect
//public class ArgumentInfoAOP
//{
////	@Pointcut("@annotation(com.tmall.common.annotation.ArgumentCheck)")
////	public void checkArgument() {}
//	
//	
//	@Before("checkArgument()")
//	public void checkArgumentBefore(JoinPoint joinPoint,ArgumentCheck argumentCheck)
//	{
//		String type = argumentCheck.type();
//		Object[] args = joinPoint.getArgs();
//		try
//		{
//			Class.forName(type);
//		} catch (ClassNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for (Object object : args)
//		{
//			if(object.getClass().getName().equals(type))
//			{
//				
//			}
//		}
//		joinPoint.getSignature();
//	}
//	
//	
//	
//}
