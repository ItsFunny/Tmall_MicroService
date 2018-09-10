/**
*
* @author joker 
* @date 创建时间：2018年9月9日 下午1:11:15
* 
*/
package com.tmall.server.store.provider.service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.model.MessageModel;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月9日 下午1:11:15
 */
public interface ICommonMessageService
{
	void insertToLocalMessageAndNotify(UserDTO user, AppEvent event, String eventJson, Integer messageStatus);

	ResultDTO<Object> addMessageJob(UserDTO user, AppEvent event, String eventJson, Integer status);

	void notifyUpdateStatus(String messageId, Integer status);

	
	Integer insert(MessageModel messageModel);

	MessageModel findById(String id);

	Integer deleteById(String id);
}
