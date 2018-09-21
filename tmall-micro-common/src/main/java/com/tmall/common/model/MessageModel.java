/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午10:48:22
* 
*/
package com.tmall.common.model;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.other.SQLExtentionModel;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 上午10:48:22
*/
@Data
public class MessageModel extends AbstractSQLExtentionModel
{
	private String messageId;
	private String messageDetail;
	private Integer messageStatus;
	
	public MessageDTO to()
	{
		MessageDTO messageDTO=new MessageDTO();
		messageDTO.setMessageId(this.messageId);
		messageDTO.setMessageDetail(this.messageDetail);
		messageDTO.setMessageStatus(this.messageStatus);
		return messageDTO;
	}
	
	public MessageModel(String id,String detail,Integer messageStatus)
	{
		this.messageId=id;
		this.messageDetail=detail;
		this.messageStatus=messageStatus;
	}

	@Override
	public Number getUniquekey()
	{
		return this.messageId.hashCode();
	}
}
