///**
//*
//* @author joker 
//* @date 创建时间：2018年9月5日 上午10:52:29
//* 
//*/
//package com.tmall.server.message.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.tm.message.common.exception.TmallMessageException;
//import com.tm.message.common.model.TmallBatchMessage;
//import com.tm.message.common.model.TmallBatchMessageExample;
//import com.tm.message.common.model.TmallBatchMessageExample.Criteria;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.server.message.dao.MessageDao;
//import com.tmall.server.message.service.IMessageService;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年9月5日 上午10:52:29
// */
//@Service
//public class MessageServiceImpl implements IMessageService
//{
//	@Autowired
//	private MessageDao messageDao;
//
//	@Override
//	public Integer insert(TmallBatchMessage messageModel)
//	{
//		return messageDao.insert(messageModel);
//	}
//
//	@Override
//	public TmallBatchMessage findById(String id)
//	{
//		TmallBatchMessageExample example = new TmallBatchMessageExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andMessageIdEqualTo(id);
//		List<TmallBatchMessage> list = messageDao.selectByExample(example);
//		if (null == list)
//		{
//			return null;
//		} else if (list.size() > 1)
//		{
//			throw new TmallMessageException(ErrorCodeEnum.FIND_MULTIPLE_RECORDS);
//		} else
//		{
//			return list.iterator().next();
//		}
//	}
//
//	@Override
//	public Integer deleteById(String id)
//	{
//		TmallBatchMessageExample example = new TmallBatchMessageExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andMessageIdEqualTo(id);
//		return messageDao.deleteByExample(example);
//	}
//
//	@Override
//	public Integer updateSelective(TmallBatchMessage message)
//	{
//		TmallBatchMessageExample example = new TmallBatchMessageExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andMessageIdEqualTo(message.getMessageId());
//		return messageDao.updateByExampleSelective(message, example);
//	}
//
//}
