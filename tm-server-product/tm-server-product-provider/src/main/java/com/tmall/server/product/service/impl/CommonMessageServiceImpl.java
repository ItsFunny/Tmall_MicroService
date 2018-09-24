/**
*
* @author joker 
* @date 创建时间：2018年9月9日 下午1:11:40
* 
*/
package com.tmall.server.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.joker.library.sqlextention.ISQLExtentionBaseCRUDDao;
import com.joker.library.sqlextention.SQLExtentionDaoWrapper;
import com.joker.library.sqlextention.SQLExtentionHolderV3;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.model.MessageModel;
import com.tmall.common.mq.MQUtil;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.other.ISQLExtentionCRUDDao;
import com.tmall.common.other.SQLExtentionDAOWrapper;
import com.tmall.common.other.SQLExtentionHolder;
import com.tmall.common.service.ICommonMessageService;
import com.tmall.server.spi.gateway.message.IGatewayMessageService;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author joker
 * @date 创建时间：2018年9月9日 下午1:11:40
 */
@Slf4j
@Service
public class CommonMessageServiceImpl implements ICommonMessageService
{
	@Autowired
	private SQLExtentionHolderV3 holder;

	@Autowired
	private IGatewayMessageService gatewayMessageService;

	@Override
	public void insertToLocalMessageAndNotify(UserDTO user, AppEvent event, String eventJson, Integer messageStatus)
	{
		MessageModel model = new MessageModel(event.getUuid(), eventJson, messageStatus);
//		SQLExtentionDAOWrapper<Object> wrapper = holder.getBaseDao(SQLExtentionConstant.MESSAGE, (long) model.getMessageId().hashCode());
		int hashCode = model.getMessageId().hashCode();
		SQLExtentionDaoWrapper<MessageModel> wrapper = holder.getBaseDao(SQLExtentionConstant.MESSAGE, hashCode);
		ISQLExtentionBaseCRUDDao<MessageModel> messageDao = wrapper.getDao();
		model.setTableName(wrapper.getTableName());
		Integer validCount = messageDao.insert(model);
//		messageDao.insertSelective(t)
		if (validCount <= 0)
		{
			// 本地消息记录失败直接返回,不进行业务插入
			// return com.joker.library.utils.ResultUtils.fail("本地记录消息失败");
			throw new TmallBussinessException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, "无法在本地插入数据");
		}
		ResultDTO<Object> res = addMessageJob(user, event, eventJson, TmallMQEnum.MQ_STATUS.NEW.ordinal());
		if (!res.isSuccess())
		{
			// 发送失败则手动抛异常,回滚
			throw new TmallBussinessException(
					ErrorCodeEnum.parseEnum(ErrorCodeEnum.TRANSCATION_ROLLBACK_ON_PURPOSE, "message服务中心插入消息数据失败"));
		}

	}

	@Override
	public ResultDTO<Object> addMessageJob(UserDTO user, AppEvent event, String eventJson, Integer status)
	{
		MessageDTO dto = MQUtil.createMessageDTO(user, event, eventJson, status);
		ResultDTO<Object> resultDTO = gatewayMessageService.addMessageJob(dto);
		if (!resultDTO.isSuccess())
		{
			// 发送失败则手动抛异常,回滚
			throw new TmallBussinessException(
					ErrorCodeEnum.parseEnum(ErrorCodeEnum.TRANSCATION_ROLLBACK_ON_PURPOSE, "message服务中心插入消息数据失败"));
		}
		return resultDTO;
	}

	@Override
	public void notifyUpdateStatus(String messageId, Integer status)
	{
//		throw new RuntimeException("第三块,远程服务手动失败");
		ResultDTO<String> resultDTO = gatewayMessageService.updateMessageStatus(messageId, status);
		if (!resultDTO.isSuccess())
		{
			// 手动抛出异常,其实在这里异步方式更好
			log.error("[addBrand] 调用远程消息服务试图插入message失败:原因:{}", resultDTO.getMsg());
			throw new TmallBussinessException(ErrorCodeEnum.INTERNAL_SERVICE_CALL_RETURN_FAIL, resultDTO.getMsg());
		}
	}

	@Override
	public Integer insert(MessageModel model)
	{
//		return messageDao.insert(model);
		return null;
	}

	@Override
	public MessageModel findById(String id)
	{
//		return messageDao.findById(id);
		return null;
	}

	@Override
	public Integer deleteById(String id)
	{
//		return messageDao.deleteById(id);
		return null;
	}

}
