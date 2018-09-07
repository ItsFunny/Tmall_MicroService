///**
//*
//* @author joker 
//* @date 创建时间：2018年9月5日 下午7:46:58
//* 
//*/
//package com.tmall.system.admin.mq;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.joker.library.mq.AppEvent;
//import com.joker.library.mq.AppEventPublisher;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.common.exception.TmallBussinessException;
//import com.tmall.common.mq.AbstractTmallConfirmCallback;
//import com.tmall.common.mq.TmallCorrelationDataWrapper;
//import com.tmall.system.admin.service.IMessageService;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年9月5日 下午7:46:58
// */
//@Slf4j
//public class TmallUserRecordConfirmCallback extends AbstractTmallConfirmCallback
//{
//
//	@Autowired
//	private AppEventPublisher eventPublisher;
//
//	public TmallUserRecordConfirmCallback(String type)
//	{
//		super(type);
//	}
//
//	@Override
//	protected void doSuccessConfirm(TmallCorrelationDataWrapper wrapper)
//	{
//		//
//		// 成功的话删除记录
//		String id = wrapper.getId();
//		Integer validCount = messageService.deleteById(id);
//		if (validCount > 0)
//		{
//			log.info("[doSuccessConfirm]消息:{}发送成功", wrapper);
//		} else
//		{
//			log.error("[doSuccessConfirm]消息:{}发生成功,但无法从数据库中删除对应的记录(获取无记录),messageId:{}", wrapper, wrapper.getId());
//			throw new TmallBussinessException(ErrorCodeEnum.MQ_MESSAGE_CANT_FIND_MATCH_RECORD_FORM_DB);
//		}
//	}
//
//	@Override
//	protected void doFailConfirm(TmallCorrelationDataWrapper wrapper, String cause)
//	{
//		// 失败的话重复发送
//		log.error("[用户操作记录消息]无法发送到指定消息队列中,原因为:{}", cause);
//		AppEvent callBackEvent = wrapper.getCallBackData();
//		if (null == callBackEvent)
//		{
//			throw new TmallBussinessException(ErrorCodeEnum.MQ_CALLBACK_ARGUMENT_CANT_BE_NULL);
//		}
//		log.info("[用户操作日志记录]:尝试重新发送消息");
//		eventPublisher.publish(callBackEvent);
//	}
//
//}
