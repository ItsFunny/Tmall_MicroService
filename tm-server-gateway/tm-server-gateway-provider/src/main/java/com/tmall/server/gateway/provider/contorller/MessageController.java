/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:59:53
* 
*/
package com.tmall.server.gateway.provider.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.tmall.common.dto.MessageDTO;
import com.tmall.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午5:59:53
 */
@RestController
@RequestMapping(value = "/valid/message")
public class MessageController
{
	@Autowired
	private IFacadedService facdedService;

	@PostMapping(value = "/addMessageJob", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Object> addMessageJob(@RequestBody MessageDTO messageDTO)
	{
		return facdedService.addMessageJob(messageDTO);
	}

	@GetMapping(value = "/update/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> updateMessage(@RequestParam("messageId") String messageId,
			@RequestParam("messageStatus") Integer messageStatus)
	{
		return facdedService.updateMessageStatus(messageId, messageStatus);
	}

}
