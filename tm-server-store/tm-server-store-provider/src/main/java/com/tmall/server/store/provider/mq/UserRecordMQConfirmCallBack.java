///**
//*
//* @author joker 
//* @date 创建时间：2018年9月5日 上午9:35:17
//* 
//*/
//package com.tmall.server.store.provider.mq;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.joker.library.mq.AbstractMQConfirmCallback;
//import com.joker.library.mq.AppEvent;
//import com.joker.library.mq.AppEventPublisher;
//import com.joker.library.mq.AppEventRabbitMQPublish;
//import com.joker.library.utils.JsonUtil;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.common.exception.TmallBussinessException;
//import com.tmall.common.exception.TmallParseBeanException;
//import com.tmall.facade.service.ICommonMessageService;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年9月5日 上午9:35:17
// */
//@Slf4j
//public class UserRecordMQConfirmCallBack extends AbstractMQConfirmCallback<UserRecordCorrleationData>
//{
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//	@Autowired
//	private AppEventPublisher eventPublisher;
//
////	@Autowired
////	private ICommonMessageService messageService;
//
//	public UserRecordMQConfirmCallBack(Class type)
//	{
//		super(UserRecordCorrleationData.class);
//	}
//
//	@Override
//	public void doSuccess(CorrelationData correlationData)
//	{
//		// delete from db
//		log.info("[监听消息] 消息发送成功,关联信息为:{}", correlationData);
//		if (null == correlationData)
//		{
//			throw new TmallBussinessException(
//					ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_MQ_MISSING_ARGUMETN, "参数correlateionData不能为空"));
//		}
//		String uuid = correlationData.getId();
//		//为什么这里要删除啊....我自己都忘了,以前也删除的嘛
//		Integer validCount = messageService.deleteById(uuid);
//		if (validCount > 0)
//		{
//			log.info("[监听消息],成功从db中删除对应的一条message中的记录:{}", uuid);
//		} else
//		{
//			log.error("[监听消息],无法从db中删除对应的一条message记录,id为:{},可能在数据库中记录不存在", uuid);
//		}
//	}
//
//	@Override
//	public void doFail(CorrelationData correlationData, String cause)
//	{
//		log.error("[发送消息失败]原因为:{}",cause);
//		// 尝试重新发送
//		if (correlationData instanceof UserRecordCorrleationData)
//		{
//			UserRecordCorrleationData data=(UserRecordCorrleationData) correlationData;
//			String json = data.getData();
//			AppEvent event = JsonUtil.json2Object(json, AppEvent.class);
//			
//			int i=0;
//			while(i<4)
//			{
//				log.info("[监听消息]尝试第{}次重新发送消息",i++);
//				
//			}
//		} else
//		{
//			throw new TmallParseBeanException(ErrorCodeEnum.STORE_MQ_NOT_SUPPORT_TYPE);
//		}
//	}
//
//}
