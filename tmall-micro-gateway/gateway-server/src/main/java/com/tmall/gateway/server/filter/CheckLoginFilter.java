///**
//*
//* @author joker 
//* @date 创建时间：2018年6月11日 下午6:34:41
//* 
//*/
//package com.tmall.gateway.server.filter;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.http.HttpStatus;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.tmall.common.constants.SessionConstant;
//import com.tmall.common.constants.TmallURLConstant;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年6月11日 下午6:34:41
//*/
//@Component
//public class CheckLoginFilter extends ZuulFilter
//{
//	private static String[] urls=new String[] {
//			"/gateway/login/checkLogin/email",
//			"/gateway/login/checkLogin/openid",
//	};
//	private boolean isInWhiteUriRequest(String uri)
//	{
//		for (String string : urls)
//		{
//			if(string.equals(uri))
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	@Override
//	public Object run() throws ZuulException
//	{
//		RequestContext currentContext = RequestContext.getCurrentContext();
//		HttpServletRequest request = currentContext.getRequest();
//		HttpServletResponse response = currentContext.getResponse();
//		HttpSession session = request.getSession(true);
//		if(null!=session.getAttribute(SessionConstant.markIsAuth))
//		{
//			
//		}
//		return null;
//	}
//
//	@Override
//	public boolean shouldFilter()
//	{
////		RequestContext ctx = RequestContext.getCurrentContext();
////		HttpServletRequest request = ctx.getRequest();
////		HttpSession session = request.getSession(true);
////		String redirectUrl = request.getParameter("redirectUrl");
////		redirectUrl=StringUtils.isEmpty(redirectUrl)?TmallURLConstant.TMALL_PORTAL_INDEX_URL:redirectUrl;
////		if(null!=session.getAttribute(SessionConstant.markIsAuth))
////		{
////			String encryptToken = (String) session.getAttribute(SessionConstant.USER_LOGIN_TOKEN_IN_SESSION);
////			if(!StringUtils.isEmpty(encryptToken))
////			{
////				HttpServletResponse response = ctx.getResponse();
////				try
////				{
////					response.sendRedirect(redirectUrl+"token="+URLEncoder.encode(encryptToken, "UTF-8"));
////				} catch (IOException e)
////				{
////					e.printStackTrace();
////					return true;
////				}
////			}
////		}
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//		String uri = request.getRequestURI();
//		if(isInWhiteUriRequest(uri))
//		{
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public int filterOrder()
//	{
//		return 0;
//	}
//
//	@Override
//	public String filterType()
//	{
//		return FilterConstants.PRE_TYPE;
//	}
//
//}
