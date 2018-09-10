/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:44:02
* 
*/
package com.tmall.server.spi.message.fallback;

import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.dto.MessageDTO;
import com.tmall.server.spi.message.IMessageServerFeignService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午5:44:02
 */

//@Component
@Slf4j
public class MessageServerFeignServiceFallback implements IMessageServerFeignService
{

	@Override
	public ResultDTO<Object> addMessageJob(MessageDTO messageDTO)
	{
		log.error("[消息服务器]本地添加消息任务失败,event:{}", messageDTO);
		return ResultUtils.fail();
	}

	@Override
	public ResultDTO<String> updateMessageStatus(String messageId, Integer status)
	{
		log.error("[消息服务器]本地更新消息任务失败,messageId:{},试图修改的状态为:{}",messageId, status);
		return ResultUtils.fail();
	}

}
