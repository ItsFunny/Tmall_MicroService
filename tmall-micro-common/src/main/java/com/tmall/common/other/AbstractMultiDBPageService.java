///**
//*
//* @author joker 
//* @date 创建时间：2018年9月16日 上午9:36:32
//* 
//*/
//package com.tmall.common.other;
//
//import java.util.Map;
//
//import com.joker.library.page.AbstractPageService;
//import com.joker.library.page.PageBaseService;
//import com.joker.library.page.PageRequestDTO;
//import com.joker.library.page.PageResponseDTO;
//import com.tmall.common.dto.PageExample;
//
///**
//* 
//* @author joker 
//* @date 创建时间：2018年9月16日 上午9:36:32
//*/
//public abstract class AbstractMultiDBPageService<E extends PageExample ,P> extends AbstractPageService<P>
//{
//	public  P findByCondition(E e)
//	{
//		return null;
//	}
//
//	@Override
//	public P findByCondition(Integer start, Integer end, Map<String, Object> condition)
//	{
//		PageExample example=new PageExample();
//		example.setEnd(end);
//		example.setStart(start);
//		return null;
//	}
//
//	@Override
//	public P findAllByPage(Integer start, Integer end)
//	{
//		return null;
//	}
//
//	@Override
//	public Long countByCondition(Map<String, Object> condition)
//	{
//		return null;
//	}
//	
//	
//
//}
