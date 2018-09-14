///**
//*
//* @author joker 
//* @date 创建时间：2018年8月18日 下午12:07:41
//* 
//*/
//package com.tmall.common.interceptor;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.tmall.common.constants.AuthConstant;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年8月18日 下午12:07:41
//*/
//public class RestTemplateInterceptor implements ClientHttpRequestInterceptor
//{
//
//	@Override
//	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//			throws IOException
//	{
//		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest req = attributes.getRequest();
//		String token=(String) req.getSession(true).getAttribute(AuthConstant.AUTH_IN_SESSION);
//		System.out.println(token);
//		
//		
//		return null;
//	}
//
//}
