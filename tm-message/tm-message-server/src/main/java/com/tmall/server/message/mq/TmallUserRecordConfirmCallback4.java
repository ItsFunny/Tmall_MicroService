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
//public class TmallUserRecordConfirmCallback4 extends AbstractTmallConfirmCallback implements WebMvcConfigurer
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
//	public TmallUserRecordConfirmCallback4(String type)
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
//	protected void doSuccessConfirm(TmallCorrelationDataWrapper wrapper)
//	{
//		/*
//		 * 更新本地消息为send_success
//		 */
//		String messageId = wrapper.getId();
//		JobWrapper jobWrapper = jobHolder.get(messageId);
//		if (null == jobWrapper)
//		{
//			/*
//			 * 说明可能是超时时间太短被误删除了 这时候需要找到具体的服务然后查询任务状态
//			 */
//			// 这里还需要判断异常情况,是否失败等情况
//			Map<String, Object> conditions = new HashMap<String, Object>();
//			conditions.put("type", ConfigTemplateEnum.Type.MESSAGE_VERIFY.ordinal());
//			ResultDTO<List<TmallConfigTemplateDTO>> resultDTO = gatewayAuthFeignService.getConfigTemplates(conditions);
//			if (!resultDTO.isSuccess())
//			{
//				// 说明调用失败
//				log.error("[doSuccessConfirm]尝试通过rest请求获取消息状态检测配置信息失败,原因:{}", resultDTO.getMsg());
//				// 直接结束
//				return;
//			}
//			List<TmallConfigTemplateDTO> data = resultDTO.getData();
//			TmallConfigTemplateDTO dto = null;
//			if (null != data && data.size() == 1)
//			{
//				dto = data.iterator().next();
//				 ResultDTO<MessageDTO> result = verifyMessageStatus(wrapper.getServerName(), dto.getTemplateDetial());
//				 if(!result.isSuccess())
//				 {
//					//失败说明服务调用失败,不管,直接退出:
//					 return;
//				 }else {
//					MessageDTO messageDTO = result.getData();
//					if (null==messageDTO)
//					{
//						//不过这里存在大大的逻辑问题,那就是如果消息发布成功了,但是呢上游服务却并没有执行成功,
//						//发布的前提是上游服务执行成功->通知消息服务器发送消息->这个不可能为空才对
//						 jobHolder.delete(messageId);
//					}else {
//						//有值说明是执行完成的,因为如果执行没完成是记录都没的
//						//那么就是重新构建对象,然后结束即可
//						String eventJson = messageDTO.getMessageDetail();
//						AppEvent event = JsonUtil.json2Object(eventJson, AppEvent.class);
//						jobWrapper=new JobWrapper();
//						jobWrapper.setEvent(event);
////						jobWrapper.setStatus(JobWrapper.SEND_SUCCESS);
////						eventPublisher.publish(event);
//					}
//				}
//				// 如果为空,说明是上述error中3出错,服务本地业务可能失败/成功之后通知消息服务器失败,则直接删除
//				// 本地消息表中有2种状态,new和finished,new表明刚生成还未处理,finished表明已经处理了
//				// 验证的情况是,如果rest请求传回来有值,说明已经处理
//			}
//		} 
//		//更新状态
//		jobWrapper.setStatus(JobWrapper.SEND_SUCCESS);
//		//至于要不要落地,再说
//	}
//
//	@Override
//	protected void doFailConfirm(TmallCorrelationDataWrapper wrapper, String cause)
//	{
//	}
//
//}
