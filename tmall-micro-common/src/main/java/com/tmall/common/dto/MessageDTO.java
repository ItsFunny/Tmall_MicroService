/**
*
* @author joker 
* @date 创建时间：2018年9月6日 上午10:49:18
* 
*/
package com.tmall.common.dto;

import java.util.Date;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月6日 上午10:49:18
 */
@Data
public class MessageDTO
{
	private String messageId;

	private String messageDetail;

	private String messageFrom;

	private Integer messageStatus;

	private String messageCreator;

	private Long messageUserId;

	private Date createDate;

	private Date updateDate;
}
