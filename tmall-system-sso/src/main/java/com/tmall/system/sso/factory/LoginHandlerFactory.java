///**
//*
//* @Description
//* @author joker 
//* @date 创建时间：2018年7月29日 下午10:14:31
//* 
//*/
//package com.tmall.system.sso.factory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.tmall.server.user.service.IUserService;
//import com.tmall.system.sso.service.AbstractLoginHandler;
//import com.tmall.system.sso.service.ILoginService;
//import com.tmall.system.sso.service.impl.EmailLoginHandler;
//
///**
//* 
//* @When
//* @Description
//* @Detail
//* @author joker 
//* @date 创建时间：2018年7月29日 下午10:14:31
//*/
//
//public class LoginHandlerFactory
//{
//	
//	@Autowired
//	private ILoginService loginService;
//	@Autowired
//	private IUserService userService;
//	
//	private AbstractLoginHandler loginHandler;
//	
//	public <T extends AbstractLoginHandler> AbstractLoginHandler create(Class<T> class1)
//	{
//		try
//		{
//			loginHandler=new EmailLoginHandler(AbstractLoginHandler.EMAIL_TYPE);
//			
//			loginHandler=class1.newInstance();
//			loginHandler.init(userService, loginService);
//		} catch (InstantiationException e)
//		{
//			e.printStackTrace();
//		} catch (IllegalAccessException e)
//		{
//			e.printStackTrace();
//		}
//		return loginHandler;
//	}
//}
