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
//import com.tm.message.common.exception.TmallMessageException;
//import com.tm.message.common.model.TmallBatchMessage;
//import com.tmall.common.dto.MessageDTO;
//import com.tmall.common.dto.TmallConfigTemplateDTO;
//import com.tmall.common.enums.ConfigTemplateEnum;
//import com.tmall.common.enums.ErrorCodeEnum;
//import com.tmall.common.exception.TmallBussinessException;
//import com.tmall.common.mq.AbstractTmallConfirmCallback;
//import com.tmall.server.message.holder.JobHolder;
//import com.tmall.server.message.holder.JobWrapper;
//import com.tmall.server.message.service.IMessageService;
//import com.tmall.server.spi.gateway.auth.IGatewayAuthFeignService;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年9月5日 下午7:46:58
// */
//@Slf4j
//public class TmallUserRecordConfirmCallback3 extends AbstractTmallConfirmCallback implements WebMvcConfigurer
//{
//
//	@Autowired
//	private IMessageService messageService;
//	@Autowired
//	private AppEventPublisher eventPublisher;
//	@Autowired
//	private JobHolder jobHolder;
//	@Autowired
//	private IGatewayAuthFeignService gatewayAuthFeignService;
//	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
//	@Autowired
//	private RestTemplate restTemplate;
//
//	public TmallUserRecordConfirmCallback3(String type)
//	{
//		super(type);
//	}
//	private MessageDTO verifyMessageStatus(AppEvent event,String visitUrl)
//	{
//		ServiceInstance instance = loadBalancerClient.choose(event.getServerName());
//		if(null==instance)
//		{
//			throw new TmallMessageException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.MQ_MESSAGE_MISSING_SERVER_NAME, event.getServerName()));
//		}
//		String host = instance.getHost();
//		int port = instance.getPort();
//		String url="http://"+host+":"+port+"/"+visitUrl;
//		try
//		{
//			ResultDTO resultDTO = restTemplate.getForObject(url, ResultDTO.class);
//			if(!resultDTO.isSuccess())
//			{
//				return null;
//			}else {
//				return  (MessageDTO) resultDTO.getData();
//			}
//		} catch (Exception e)
//		{
//			log.error("[verifyMessageStatus]尝试获取通过rest请求获取服务消费状态失败,原因:{}",e.getMessage(),e);
//			return null;//说明无效,然后啥也不管
//		}
//	
//	
//	}
//	@Override
//	protected void doSuccessConfirm(MicroCorrelationData wrapper)
//	{
//		// 打印发送成功日志
//		log.info("[doSuccessConfirm]消息:{}发送成功", wrapper);
//		AppEvent callBackEvent=wrapper.getCallBackData();
//		// 缓存更新状态:
//		// 成功的话更新记录
//		String id = wrapper.getId();
//		JobWrapper jobWrapper = jobHolder.get(id);
//		if (null != jobWrapper)
//		{
//			jobWrapper.setStatus(JobWrapper.SEND_SUCCESS);
//			log.info("[doSuccessConfirm]成功更新消息状态:消息:{}", jobWrapper);
//		} else
//		{
//			//2018-09-07 17:17 算了,逻辑有点复杂,先放着了
//			log.info("[doSuccessConfirm]消息id为:{}的消息丢失了,可能原因为超时时间设置过短", id);
//			// 从上游服务器中回溯该任务的详细
//			// 消息丢失了,很可能是超时时间设置过短,然后后台线程自动清理了,这时候需要调整超时时间
//			/*
//			 * 比如说有个后台线层,恰巧在插入的时候运行,然后超时时间设置太短,刚好上游服务查询也还没执行完毕(db中无记录),则就会把这个删了
//			 * 然后这个线程执行到这里就为空了,然后就需要调用上游服务接口查看是否已经执行了(执行到这里上游是肯定执行了其本地业务的,因为publish说明是收到了通知的
//			 * ,而通知是在执行本地业务之后的)
//			 * 
//			 * 又衍生一个问题,该如何确定上游服务呢:答案就是通过这个jobWrapper中的appEvent中的serverName属性,
//			 * 然后通过从db模板中查询消息查询接口的url,然后拼接调用即可,至于这个该放在哪个服务,想了想还是放在auth吧
//			 */
//			Map<String, Object>conditions=new HashMap<String, Object>();
//			conditions.put("type", ConfigTemplateEnum.Type.MESSAGE_VERIFY.ordinal());
//			//这里还需要判断异常情况,是否失败等情况
//			ResultDTO<List<TmallConfigTemplateDTO>> resultDTO = gatewayAuthFeignService.getConfigTemplates(conditions);
//			if(!resultDTO.isSuccess())
//			{
//				//说明调用失败
//				log.error("[doSuccessConfirm]尝试通过rest请求获取消息状态检测配置信息失败,原因:{}",resultDTO.getMsg());
//				//直接结束
//				return;
//			}
//			List<TmallConfigTemplateDTO> data = resultDTO.getData();
//			TmallConfigTemplateDTO dto=null;
//			if(null!=data && data.size()==1)
//			{
//				dto=data.iterator().next();
//				MessageDTO messageDTO = verifyMessageStatus(callBackEvent, dto.getTemplateDetial());
//				//本地消息表中有2种状态,new和finished,new表明刚生成还未处理,finished表明已经处理了
//				//验证的情况是,如果rest请求传回来有值,说明已经处理
//			}
//		
//			throw new TmallMessageException(ErrorCodeEnum.MQ_MESSAGE_MISSING);
//		}
//		//发送成功之后更新本地状态为SEND_SUCCESS :发送成功,不过为啥要在本地db中更新啊..蛋疼,才发现在本地也更新了
////		TmallBatchMessage message = new TmallBatchMessage();
////		message.setMessageId(id);
////		message.setMessageStatus(JobWrapper.SEND_SUCCESS);
////		Integer validCount = messageService.updateSelective(message);
////		if (validCount > 0)
////		{
////			log.info("[doSuccessConfirm]消息:{}本地更新成功,wrapper:{}", message, wrapper);
////		} else
////		{
////			log.error("[doSuccessConfirm]消息:{}发生成功,但无法在数据库更新对应的记录(获取无记录),messageId:{}", wrapper, wrapper.getId());
////			throw new TmallBussinessException(ErrorCodeEnum.MQ_MESSAGE_CANT_FIND_MATCH_RECORD_FORM_DB);
////		}
//	}
//
//	@Override
//	protected void doFailConfirm(MicroCorrelationData wrapper, String cause)
//	{
//		// 失败的话重复发送
//		log.error("[用户操作记录消息]无法发送到指定消息队列中,原因为:{}", cause);
//		String id = wrapper.getId();
//		JobWrapper jobWrapper = jobHolder.get(id);
//		if (null != jobWrapper)
//		{
//			jobWrapper.setStatus(JobWrapper.SEND_SUCCESS);
//			log.info("[doSuccessConfirm]成功更新消息状态:消息:{}", jobWrapper);
//		} else
//		{
//			// 消息丢失了,很可能是超时时间设置过短,然后后台线程自动清理了,这时候需要调整超时时间
//			log.info("[doSuccessConfirm]消息id为:{}的消息丢失了,可能原因为超时时间设置过短", id);
//			throw new TmallMessageException(ErrorCodeEnum.MQ_MESSAGE_MISSING);
//		}
//
//		AppEvent callBackEvent = wrapper.getCallBackData();
//		if (null == callBackEvent)
//		{
//			throw new TmallBussinessException(ErrorCodeEnum.MQ_CALLBACK_ARGUMENT_CANT_BE_NULL);
//		}
//		log.info("[用户操作日志记录]:消息:{}尝试重新发送消息", callBackEvent);
//		eventPublisher.publish(callBackEvent);
//		// 更新本地状态为失败
////		TmallBatchMessage message = new TmallBatchMessage();
////		message.setMessageId(callBackEvent.getUuid());
////		message.setMessageStatus(JobWrapper.SEND_FAIL);
////		Integer validCount = messageService.updateSelective(message);
////		if (validCount <= 0)
////		{
////			log.error("[doFailConfirm]消息发送失败,尝试本地更新状态为send_fail失败,可能是数据库中没有这条记录:消息为:{}", message);
////		} else
////		{
////			log.info("[doFailConfirm]消息发送失败,成功更新本地消息状态为sned_fail.message:{}", message);
////		}
//	}
//
//}
