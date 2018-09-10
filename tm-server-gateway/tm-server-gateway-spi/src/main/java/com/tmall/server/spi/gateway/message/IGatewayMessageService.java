/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午6:02:58
* 
*/
package com.tmall.server.spi.gateway.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.server.spi.gateway.fallback.GatewayMessageFallbackService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午6:02:58
 */
@FeignClient(name = "gateway", fallback = GatewayMessageFallbackService.class)
public interface IGatewayMessageService
{
	@PostMapping(value="/valid/message/addMessageJob")
	ResultDTO<Object>addMessageJob(@RequestBody MessageDTO messageDTO);
	
	@GetMapping(value="/valid/message/update/status",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>updateMessageStatus(@RequestParam("messageId")String messageId,@RequestParam("messageStatus")Integer status);
}
