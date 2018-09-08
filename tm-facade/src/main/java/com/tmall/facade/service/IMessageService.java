/**
*
* @author joker 
* @date 创建时间：2018年9月8日 上午9:10:03
* 
*/
package com.tmall.facade.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.MessageDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 上午9:10:03
*/
public interface IMessageService
{
	ResultDTO<String>updateMessageStatus(String messageId,Integer status);
	
	ResultDTO<Object>addMessageJob(@RequestBody MessageDTO messageDTO);
	
}
