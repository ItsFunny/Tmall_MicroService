/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:28:40
* 
*/
package com.tmall.server.message.controller;

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
import com.joker.library.mq.AppEventPublisher;
import com.joker.library.utils.JsonUtil;
import com.joker.library.utils.ResultUtils;
import com.tm.message.common.model.TmallBatchMessage;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.server.message.holder.JobHolder;
import com.tmall.server.message.holder.JobWrapper;
import com.tmall.server.message.service.IMessageService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午5:28:40
 */
@RestController
@RequestMapping("/message")
public class JobController
{
	@Autowired
	private JobHolder jobHolder;
//	@Autowired
//	private IMessageService messageService;

	@Autowired
	private AppEventPublisher eventPublisher;

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Object> addJob(@RequestBody MessageDTO messageDTO)
	{
		// 在本地message中添加记录
		TmallBatchMessage model = new TmallBatchMessage();
		model.from(messageDTO);
//		Integer validCount = messageService.insert(model);
		String json = messageDTO.getMessageDetail();
		AppEvent event = JsonUtil.json2Object(json, AppEvent.class);
		try
		{
			jobHolder.addJob(event);
			event.setServerName(messageDTO.getMessageFrom());
			return ResultUtils.sucess();
		} catch (Exception e)
		{
			return ResultUtils.fail(e.getMessage());
		}
//		if (validCount > 0)
//		{
//			return ResultUtils.sucess();
//		} else
//		{
//			return ResultUtils.fail("本地插入消息记录失败");
//		}
	}

	@GetMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> notifySendMsg(@RequestParam("messageId") String messageId,
			@RequestParam("messageStatus") Integer status)
	{
		JobWrapper jobWrapper = jobHolder.get(messageId);
		if (null == jobWrapper)
		{
			return ResultUtils.fail("消息不存在");
		}
		jobWrapper.setStatus(status);
		if (status.equals(TmallMQEnum.MQ_STATUS.READY.ordinal()))
		{
			AppEvent event = jobWrapper.getEvent();
			eventPublisher.publish(event);
		}
		return ResultUtils.sucess();
//		TmallBatchMessage message=new TmallBatchMessage();
//		message.setMessageId(messageId);
//		message.setMessageStatus(status);
//		Integer validCount = messageService.updateSelective(message);
//		if(validCount>0)
//		{
//			return ResultUtils.sucess();
//		}else {
//			return ResultUtils.fail("更新失败,错误未知");
//		}
	}
}
