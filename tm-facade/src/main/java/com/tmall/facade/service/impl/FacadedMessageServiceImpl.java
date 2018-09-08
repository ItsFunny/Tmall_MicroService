/**
*
* @author joker 
* @date 创建时间：2018年9月8日 上午9:10:36
* 
*/
package com.tmall.facade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.facade.service.IMessageService;
import com.tmall.server.spi.message.IMessageServerFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 上午9:10:36
*/
@Service
public class FacadedMessageServiceImpl implements IMessageService
{
	@Autowired
	private IMessageServerFeignService messageServerFeignService;
	@Override
	public ResultDTO<String> updateMessageStatus(String messageId, Integer status)
	{
		return messageServerFeignService.updateMessageStatus(messageId, status);
	}
	@Override
	public ResultDTO<Object> addMessageJob(MessageDTO messageDTO)
	{
		return messageServerFeignService.addMessageJob(messageDTO);
	}

}
