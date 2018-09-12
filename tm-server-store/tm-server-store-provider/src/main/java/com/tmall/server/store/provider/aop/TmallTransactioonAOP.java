/**
*
* @author joker 
* @date 创建时间：2018年9月9日 上午11:32:48
* 
*/
package com.tmall.server.store.provider.aop;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.utils.JsonUtil;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.annotation.RabbitMQTransaction;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.exception.TmallParseBeanException;
import com.tmall.common.mq.MQUtil;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.store.provider.service.ICommonMessageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月9日 上午11:32:48
 */
@Slf4j
@Aspect
@Component
public class TmallTransactioonAOP
{

	@Autowired
	private ICommonMessageService commonMessageService;

	@Autowired
	private TmallConfigProperty configProperty;

	@Pointcut(value = "@annotation(com.tmall.common.annotation.RabbitMQTransaction)", argNames = "proceedingJinPoing,transaction")
	public void dateTransactionWithMessage()
	{

	}

	@SuppressWarnings("unchecked")
	@Around(value = "dateTransactionWithMessage()")
	public ResultDTO<String> confirmDateIsolutation(ProceedingJoinPoint proceedingJoinPoint)
	{
		// RabbitMQTransaction rabbitMQTransaction = (RabbitMQTransaction)
		// proceedingJoinPoint.getSignature();
//		Method realMethod = null;
//		try
//		{
//			realMethod = proceedingJoinPoint.getTarget().getClass()
//					.getDeclaredMethod(proceedingJoinPoint.getSignature().getName());
//		} catch (NoSuchMethodException e1)
//		{
//			log.error("[confirmDateIsolutation]解析获取注解时候失败,失败原因为:{}", e1.getMessage());
//			throw new TmallParseBeanException(ErrorCodeEnum.ARGUMENT_PARSE_ERROR, e1);
//		} catch (SecurityException e1)
//		{
//			log.error("[confirmDateIsolutation]解析获取注解时候失败,失败原因为:{}", e1.getMessage());
//			throw new TmallParseBeanException(ErrorCodeEnum.ARGUMENT_PARSE_ERROR, e1);
//		}
//		RabbitMQTransaction rabbitMQTransaction = realMethod.getAnnotation(RabbitMQTransaction.class);
//		int index = rabbitMQTransaction.wrapperParamIndex();
		UserRecordAspectWrapper<?> wrapper=null;
		Object[] args = proceedingJoinPoint.getArgs();
		boolean isExist=false;
		for (Object object : args)
		{
			if(object instanceof UserRecordAspectWrapper<?>)
			{
				isExist=true;
				wrapper=(UserRecordAspectWrapper<?>) object;
			}
		}
		if(!isExist)
		{
			throw new TmallBussinessException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.TYPE_ILLEGAL_ARGUMENT,
					"需要的类型为:" + UserRecordAspectWrapper.class.getName()));
		}
		String exchangeName = wrapper.getExchangeName();
		String routingKey = wrapper.getRoutingKey();
		if (StringUtils.isEmpty(exchangeName) || StringUtils.isEmpty(routingKey))
		{
			throw new TmallBussinessException(
					ErrorCodeEnum.parseEnum(ErrorCodeEnum.MISSING_ARGUMENT, "消息发送的exchangeName和routingKey不能为空"));
		}

		UserDTO user = wrapper.getUser();
		AppEvent event = MQUtil.createEvent(user, wrapper.getDetail(), exchangeName, routingKey,
				configProperty.getApplicationName());
		String eventJson = JsonUtil.obj2Json(event);
		commonMessageService.insertToLocalMessageAndNotify(user, event, eventJson, TmallMQEnum.MQ_STATUS.NEW.ordinal());
		try
		{
			proceedingJoinPoint.proceed();
		} catch (Throwable e)
		{
			log.error("[TmallTransactioonAOP]调用本地业务的时候出错:{}", e.getMessage());
			throw new TmallBussinessException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, e);
		}
		// 执行到这里说明前面的都是成功的,因为如果失败都会手动抛出异常进行回滚

		commonMessageService.notifyUpdateStatus(event.getUuid(), TmallMQEnum.MQ_STATUS.READY.ordinal());
		return ResultUtils.sucess();
	}

}
