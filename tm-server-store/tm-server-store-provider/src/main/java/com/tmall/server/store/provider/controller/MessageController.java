/**
*
* @author joker 
* @date 创建时间：2018年9月7日 下午4:37:31
* 
*/
package com.tmall.server.store.provider.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.utils.JsonUtil;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.model.MessageModel;
import com.tmall.server.store.common.exception.TmallStoreException;
import com.tmall.server.store.provider.service.ICommonMessageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月7日 下午4:37:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth/mq")
public class MessageController
{
	@Autowired
	private ICommonMessageService messageService;

	@GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<MessageDTO> checkMessageStatus(@RequestParam("messageId") String messageId)
	{
		MessageModel messageModel = messageService.findById(messageId);
		if (null == messageModel)
		{
			return ResultUtils.sucess();
		}
		// String json = messageModel.getMessageDetail();
		// if(StringUtils.isEmpty(json))
		// {
		// throw new
		// TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_LOCALMESSAGE_ILLEGAL_DATA,
		// "messageDetail","为空"));
		// }
		return ResultUtils.sucess(messageModel.to());
	}

}
