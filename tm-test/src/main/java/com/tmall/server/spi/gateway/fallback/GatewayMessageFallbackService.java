/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午6:03:27
* 
*/
package com.tmall.server.spi.gateway.fallback;

import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.dto.MessageDTO;
import com.tmall.server.spi.gateway.message.IGatewayMessageService;

import lombok.extern.slf4j.Slf4j;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 下午6:03:27
*/
@Slf4j
@Component
public class GatewayMessageFallbackService implements IGatewayMessageService
{

	@Override
	public ResultDTO<Object> addMessageJob(MessageDTO messageDTO)
	{
		log.error("[addMessageJob]gateway触发了服务降级,event:{}",messageDTO);
		return ResultUtils.fail();
	}

	@Override
	public ResultDTO<String> updateMessageStatus(String messageId, Integer status)
	{
		log.error("[updateMessageStatus]gateway更新消息状态触发了服务降级,messageId:{},status:{}",messageId,status);
		return ResultUtils.fail();
	}

}
