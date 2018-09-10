///**
//*
//* @author joker 
//* @date 创建时间：2018年9月5日 下午7:46:58
//* 
//*/
//package com.tmall.server.message.mq;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.codec.digest.XXHash32;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.joker.library.dto.ResultDTO;
//import com.joker.library.mq.AppEvent;
//import com.joker.library.mq.AppEventPublisher;
//import com.joker.library.mq.MicroCorrelationData;
//import com.joker.library.utils.JsonUtil;
//import com.joker.library.utils.ResultUtils;
//import com.tm.message.common.exception.TmallMessageException;
//import com.tm.message.common.model.TmallBatchMessage;
//import com.tmall.common.dto.MessageDTO;
//import com.tmall.common.dto.TmallConfigTemplateDTO;
//import com.tmall.common.enums.ConfigTemplateEnum;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.common.exception.TmallBussinessException;
//import com.tmall.common.mq.AbstractTmallConfirmCallback;
//import com.tmall.common.mq.TmallCorrelationDataWrapper;
//import com.tmall.server.message.holder.JobHolder;
//import com.tmall.server.message.holder.JobWrapper;
//import com.tmall.server.message.service.IMessageService;
//import com.tmall.server.spi.gateway.auth.IGatewayAuthFeignService;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 错误情况: 上游服务:1.本地消息表插入数据 2.通知消息服务器,保存消息状态为new,预备发送 3.执行本地业务 4.通知消息服务器更新状态
// * 5.发送消息 上游可能抛出的问题: 1.本地插入失败,直接回滚即可 2.通知消息服务器预备失败,回滚
// * 3.业务执行成功,消息服务器状态未更新,后台线程处理(判断是否超时,超时则调用上游服务提供的接口,判断是否已经执行,执行了则更新状态,并且发送)
// * 5.消息服务器发送消息失败,如果任务紧急则尝试重新投送,否则直接更新状态(ready,sendFail都可)
// * 
// * @author joker
// * @date 创建时间：2018年9月5日 下午7:46:58
// */
//@Slf4j
//public class TmallUserRecordConfirmCallback extends AbstractTmallConfirmCallback implements WebMvcConfigurer
//{
//
//	@Autowired
//	private AppEventPublisher eventPublisher;
//	@Autowired
//	private JobHolder jobHolder;
//
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
//	@Autowired
//	private RestTemplate restTemplate;
//
//	public TmallUserRecordConfirmCallback(String type)
//	{
//		super(type);
//	}
//
//	private ResultDTO<MessageDTO> verifyMessageStatus(String serverName, String visitUrl)
//	{
//		ServiceInstance instance = loadBalancerClient.choose(serverName);
//		if (null == instance)
//		{
//			throw new TmallMessageException(
//					ErrorCodeEnum.parseEnum(ErrorCodeEnum.MQ_MESSAGE_MISSING_SERVER_NAME, serverName));
//		}
//		String host = instance.getHost();
//		int port = instance.getPort();
//		String url = "http://" + host + ":" + port + "/" + visitUrl;
//		try
//		{
//			ResultDTO resultDTO = restTemplate.getForObject(url, ResultDTO.class);
//			return resultDTO;
//		} catch (Exception e)
//		{
//			log.error("[verifyMessageStatus]尝试获取通过rest请求获取服务消费状态失败,原因:{}", e.getMessage(), e);
//			// 这里不能单纯的返回null,如果服务挂了,但是消息是执行了的,就完蛋了,因此更改返回值
//			return ResultUtils.fail();// 说明无效,然后啥也不管
//		}
//
//	}
//
//	@Override
//	protected void doSuccessConfirm(MicroCorrelationData wrapper)
//	{
//		log.info("[doSuccessConfirm]消息:{}发送成功", wrapper);
//		String messageId = wrapper.getId();
//		JobWrapper jobWrapper = jobHolder.get(messageId);
//		jobWrapper.setStatus(JobWrapper.SEND_SUCCESS);
//	}
//
//	@Override
//	protected void doFailConfirm(MicroCorrelationData wrapper, String cause)
//	{
//		// 失败的话重复发送
//		log.error("[用户操作记录消息]无法发送到指定消息队列中,原因为:{}", cause);
//		AppEvent callBackEvent = wrapper.getCallBackData();
//		if (null == callBackEvent)
//		{
//			throw new TmallBussinessException(ErrorCodeEnum.MQ_CALLBACK_ARGUMENT_CANT_BE_NULL);
//		}
//		log.info("[用户操作日志记录]:消息:{}尝试重新发送消息", callBackEvent);
//		eventPublisher.publish(callBackEvent);
//	}
//
//}
