/**
*
* @author joker 
* @date 创建时间：2018年9月9日 上午10:29:47
* 
*/
package com.tmall.common.mq;

import java.io.Serializable;
import java.util.Date;

import com.joker.library.mq.AppEvent;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.UserDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月9日 上午10:29:47
 */
public class MQUtil
{
	/**
	 * @param user	操作的用户
	 * @param detail	操作详情,手动拼接
	 * @param exchangeName	发送的exchange名称
	 * @param routingKey	绑定的routingKey
	 * @param serverName	发送的微服务名称
	 * @return
	 * @author joker 
	 * @date 创建时间：2018年9月9日 上午10:45:04
	 */
	public static AppEvent createEvent(UserDTO user, String detail, String exchangeName, String routingKey,String serverName)
	{
		RecordDTO dto = new RecordDTO();
		dto.setRealname(user.getRealname());
		dto.setUserId(user.getUserId());
		dto.setRecordDetail(detail);
		AppEvent event = new AppEvent(exchangeName, routingKey, dto);
		event.setServerName(serverName);
		return event;
	}

	public static MessageDTO createMessageDTO(UserDTO user, AppEvent event, String eventJson,Integer msgStatus)
	{
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setCreateDate(new Date());
		messageDTO.setMessageDetail(eventJson);
		messageDTO.setMessageFrom(event.getServerName());
		messageDTO.setMessageCreator(user.getRealname());
		messageDTO.setMessageId(event.getUuid());
		messageDTO.setMessageUserId(user.getUserId());
		messageDTO.setMessageStatus(msgStatus);
		return messageDTO;
	}
}
