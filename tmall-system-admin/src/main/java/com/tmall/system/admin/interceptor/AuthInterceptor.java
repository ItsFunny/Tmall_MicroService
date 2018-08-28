///**
//*
//* @Description
//* @author joker 
//* @date 创建时间：2018年7月28日 下午9:43:36
//* 
//*/
//package com.tmall.system.admin.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tmall.common.constants.TmallURLConstant;
//import com.tmall.common.dto.AdminUserDTO;
//import com.tmall.common.utils.JsonUtils;
//import com.tmall.system.admin.config.TmallAdminConfigProperty;
//import com.tmall.system.admin.config.TmallConfigProperty;
//
///**
// * 
// * @When
// * @Description
// * @Detail
// * @author joker
// * @date 创建时间：2018年7月28日 下午9:43:36
// */
//public class AuthInterceptor implements HandlerInterceptor
//{
//
//	@Autowired
//	private TmallAdminConfigProperty tmallAdminConfigProperty;
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
//	{
//		Subject subject = SecurityUtils.getSubject();
//		if(null!=subject && null!=subject.getPrincipal())
//		{
//			return true;
//		}
//		if(null==subject || null== subject.getPrincipal())
//		{
//			String token = request.getParameter("token");
//			if(StringUtils.isEmpty(token))
//			{
//				HttpServletResponse resp=(HttpServletResponse) response;
//				resp.sendRedirect(TmallURLConstant.TMALL_AUTH_URL+tmallAdminConfigProperty.getAuthDetailUrl());
//				return false;
//			}else {
//				AdminUserDTO adminUserDTO = JsonUtils.json2Object(token, AdminUserDTO.class);
//			}
//			
//		}
//		return HandlerInterceptor.super.preHandle(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception
//	{
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception
//	{
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
//
//}
