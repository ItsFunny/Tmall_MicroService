/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午10:52:02
* 
*/
package com.tmall.server.message.service;

import com.tm.message.common.model.TmallBatchMessage;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 上午10:52:02
*/
public interface IMessageService
{
	Integer insert(TmallBatchMessage  messageModel);
	
	TmallBatchMessage findById(String id);
	
	Integer deleteById(String id);
	
	Integer updateSelective(TmallBatchMessage message);
}
