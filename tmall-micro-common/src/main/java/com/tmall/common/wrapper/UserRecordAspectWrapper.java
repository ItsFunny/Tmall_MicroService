/**
*
* @author joker 
* @date 创建时间：2018年9月11日 下午6:01:51
* 
*/
package com.tmall.common.wrapper;

import java.util.Map;

import com.tmall.common.dto.UserDTO;

import lombok.Data;

/**
 * 用于记录用户的操作日志
 * 
 * @author joker
 * @date 创建时间：2018年9月11日 下午6:01:51
 */
@Data
public class UserRecordAspectWrapper<T>
{
	private UserDTO user;

	private String detail;

	String exchangeName;

	String routingKey;

	private T data;
	
	private Map<String, Object>extraProps;
	
	public UserRecordAspectWrapper(UserDTO user,String detail,String exchangeName,String routingKey,T data)
	{
		this.user=user;
		this.detail=detail;
		this.exchangeName=exchangeName;
		this.routingKey=routingKey;
		this.data=data;
	}
}
