/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午2:59:43
* 
*/
package com.tmall.server.store.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.utils.JsonUtil;
import com.tmall.common.annotation.RabbitMQTransaction;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.mq.MQUtil;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.spi.gateway.message.IGatewayMessageService;
import com.tmall.server.store.common.exception.TmallStoreException;
import com.tmall.server.store.common.model.TmallBrand;
import com.tmall.server.store.provider.service.IBrandService;
import com.tmall.server.store.provider.service.IBrandTransactionService;
import com.tmall.server.store.provider.service.ICommonMessageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月8日 下午2:59:43
 */
@Slf4j
@Service
public class BrandTransactionServiceImpl implements IBrandTransactionService
{
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IGatewayMessageService gatewayMessageService;
	@Autowired
	private TmallConfigProperty configProperty;
	@Autowired
	private ICommonMessageService commonMessageService;

	// @Transactional(rollbackFor = Exception.class)
	// @Override
	// public ResultDTO<String> addBrand(UserDTO user, BrandDTO brandDTO)
	// {
	// TmallBrand tmallBrand = new TmallBrand();
	// MessageDTO messageDTO = null;
	// tmallBrand.from(brandDTO);
	// String detail = "增加了一个品牌,名称为:" + brandDTO.getBrandName();
	// // //recordDTO需要的元素:UserDTO(user),String(detail),
	// // RecordDTO recordDTO = new RecordDTO();
	// // recordDTO.setUserId(user.getUserId());
	// // recordDTO.setRealname(user.getRealname());
	// // recordDTO.setRecordDetail(detail);
	// // //AppEvent需要的:String(exchangName),String(routingKey),RecordDTO(recordDTO)
	// // AppEvent event = new AppEvent(TmallMQEnum.USER_RECORD.getExchangeName(),
	// // TmallMQEnum.USER_RECORD.getRoutinKey(),
	// // recordDTO);
	// AppEvent event = MQUtil.createEvent(user, detail,
	// TmallMQEnum.USER_RECORD.getExchangeName(),
	// TmallMQEnum.USER_RECORD.getRoutinKey(),configProperty.getApplicationName());
	// String json = JsonUtil.obj2Json(event);
	// // 先记录在本地消息表中
	// MessageModel messageModel = new MessageModel(event.getUuid(), json,
	// TmallMQEnum.MQ_STATUS.LOCAL_RECEIVE.ordinal());
	// Integer validCount = messageService.insert(messageModel);
	//
	// if (validCount <= 0)
	// {
	// // 本地消息记录失败直接返回,不进行业务插入
	// return com.joker.library.utils.ResultUtils.fail("本地记录消息失败");
	// } else
	// {
	// // 本地消息记录成功
	// // 发送消息到消息服务器,可以准备发送消息了
	// // messageDTO.setMessageId(event.getUuid());
	// // messageDTO.setMessageDetail(json);
	// // messageDTO.setMessageFrom(configProperty.getApplicationName());
	// messageDTO = MQUtil.createMessageDTO(user, event, json,
	// TmallMQEnum.MQ_STATUS.NEW.ordinal());
	// ResultDTO<Object> messageRes =
	// gatewayMessageService.addMessageJob(messageDTO);
	// if (!messageRes.isSuccess())
	// {
	// // 发送失败则手动抛异常,回滚
	// throw new TmallStoreException(
	// ErrorCodeEnum.parseEnum(ErrorCodeEnum.TRANSCATION_ROLLBACK_ON_PURPOSE,
	// "message服务中心插入消息数据失败"));
	// }
	// }
	// try
	// {
	// // 往消息服务器发送成功,则开始执行本地业务
	// Integer res = brandService.add(tmallBrand);
	// if (res > 0)
	// {
	// try
	// {
	// // 业务执行成功,则通知消息服务器修改消息状态,然后发送消息
	// // 这里可以同步也可以异步,为了方便,这里改为同步先
	// ResultDTO<String> resultDTO =
	// gatewayMessageService.updateMessageStatus(messageDTO.getMessageId(),
	// TmallMQEnum.MQ_STATUS.READY.ordinal());
	// if (resultDTO.isSuccess())
	// {
	// return com.joker.library.utils.ResultUtils.sucess();
	// } else
	// {
	// // 手动抛出异常,其实在这里异步方式更好
	// log.error("[addBrand] 调用远程消息服务试图插入message失败:原因:{}", resultDTO.getMsg());
	// throw new
	// TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_CALL_RETURN_FAIL,
	// resultDTO.getMsg());
	// }
	// } catch (Exception e)
	// {
	// // 手动抛出异常,其实在这里异步方式更好
	// log.error("[addBrand] 调用远程消息服务试图插入message失败:原因:{}", e.getMessage(), e);
	// // return com.joker.library.utils.ResultUtils.fail(e.getMessage());
	// throw new TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_CALL_ERROR, e);
	// }
	// // return ResultUtils.sucess();
	// } else
	// {
	// // 插入失败,则通知消息服务器修改消息状态为cancel,取消本次消息的发送
	// // 删除本地表中的消息记录,或者修改状态,当然也可以通知之后直接抛出异常回滚
	// return ResultUtils.fail("插入失败,请刷新重试");
	// }
	// } catch (Exception e)
	// {
	// // 插入失败,则通知消息服务器修改消息状态为cancel,取消本次消息的发送
	// // 删除本地表中的消息记录,或者修改状态,当然也可以通知之后直接抛出异常回滚
	// log.error("[addBrand]本地业务执行失败:原因:{}", e.getMessage(), e);
	// throw new TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, e);
	// }
	// }

	// public ResultDTO<String> doAddBrand(UserDTO user, BrandDTO brandDTO,String
	// detail)
	// {
	// TmallBrand tmallBrand = new TmallBrand();
	// tmallBrand.from(brandDTO);
	//// String detail = "增加了一个品牌,名称为:" + brandDTO.getBrandName();
	//// AppEvent event = MQUtil.createEvent(user, detail,
	// TmallMQEnum.USER_RECORD.getExchangeName(),
	//// TmallMQEnum.USER_RECORD.getRoutinKey(),
	// configProperty.getApplicationName());
	//// String eventJson = JsonUtil.obj2Json(event);
	//// commonMessageService.insertToLocalMessageAndNotify(user, event, eventJson,
	// TmallMQEnum.MQ_STATUS.NEW.ordinal());
	// try
	// {
	// // 往消息服务器发送成功,则开始执行本地业务
	// Integer res = brandService.add(tmallBrand);
	// if (res > 0)
	// {
	// try
	// {
	//// commonMessageService.notifyUpdateStatus(event.getUuid(),
	// TmallMQEnum.MQ_STATUS.READY.ordinal());
	// // 业务执行成功,则通知消息服务器修改消息状态,然后发送消息
	// // 这里可以同步也可以异步,为了方便,这里改为同步先
	// // ResultDTO<String> resultDTO =
	// // gatewayMessageService.updateMessageStatus(messageDTO.getMessageId(),
	// // TmallMQEnum.MQ_STATUS.READY.ordinal());
	// // if (resultDTO.isSuccess())
	// // {
	// // return com.joker.library.utils.ResultUtils.sucess();
	// // } else
	// // {
	// // // 手动抛出异常,其实在这里异步方式更好
	// // log.error("[addBrand] 调用远程消息服务试图插入message失败:原因:{}", resultDTO.getMsg());
	// // throw new
	// // TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_CALL_RETURN_FAIL,
	// // resultDTO.getMsg());
	// // }
	// return ResultUtils.sucess();
	// } catch (Exception e)
	// {
	// // 手动抛出异常,其实在这里异步方式更好
	// log.error("[addBrand] 调用远程消息服务试图插入message失败:原因:{}", e.getMessage(), e);
	// // return com.joker.library.utils.ResultUtils.fail(e.getMessage());
	// throw new TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_CALL_ERROR, e);
	// }
	// // return ResultUtils.sucess();
	// } else
	// {
	// // 插入失败,则通知消息服务器修改消息状态为cancel,取消本次消息的发送
	// // 删除本地表中的消息记录,或者修改状态,当然也可以通知之后直接抛出异常回滚
	// return ResultUtils.fail("插入失败,请刷新重试");
	// }
	// } catch (Exception e)
	// {
	// // 插入失败,则通知消息服务器修改消息状态为cancel,取消本次消息的发送
	// // 删除本地表中的消息记录,或者修改状态,当然也可以通知之后直接抛出异常回滚
	// log.error("[addBrand]本地业务执行失败:原因:{}", e.getMessage(), e);
	// throw new TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, e);
	// }
	// }
	public ResultDTO<String> doAddBrand(UserDTO user, BrandDTO brandDTO, String detail)
	{
		TmallBrand tmallBrand = new TmallBrand();
		tmallBrand.from(brandDTO);
		try
		{
			Integer res = brandService.add(tmallBrand);
			if (res > 0)
			{
				return ResultUtils.sucess();
			} else
			{
				// 如果本地业务执行失败,是需要回滚的,因为在本地消息表中插入了数据
				// return ResultUtils.fail("插入失败,请刷新重试");
				throw new TmallBussinessException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, "插入失败,请稍后重试");
			}
		} catch (Exception e)
		{
			log.error("[addBrand]本地业务执行失败:原因:{}", e.getMessage(), e);
			throw new TmallStoreException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, e);
		}
	}

	@RabbitMQTransaction(wrapperParamIndex = 0)
	@Override
	public ResultDTO<String> addBrand(UserRecordAspectWrapper<BrandDTO> wrapper)
	{
//		throw new RuntimeException("本地测试,插入失败");
		BrandDTO brandDTO = wrapper.getData();
		UserDTO user = wrapper.getUser();
		return doAddBrand(user, brandDTO, wrapper.getDetail());
	}
}
