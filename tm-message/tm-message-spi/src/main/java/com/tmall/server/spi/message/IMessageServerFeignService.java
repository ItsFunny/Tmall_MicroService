/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:43:14
* 
*/
package com.tmall.server.spi.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.tmall.common.dto.MessageDTO;
import com.tmall.server.spi.message.fallback.MessageServerFeignServiceFallback;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午5:43:14
 */
@FeignClient(name = "message")
public interface IMessageServerFeignService
{
	@PostMapping(value="/message/add",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<Object>addMessageJob(@RequestBody MessageDTO messageDTO);
	
	
	@GetMapping(value="/message/update",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>updateMessageStatus(@RequestParam("messageId")String messageId,@RequestParam("messageStatus")Integer status);
	
	
}
