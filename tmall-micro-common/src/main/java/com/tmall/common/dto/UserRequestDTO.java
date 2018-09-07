/**
*
* @author joker 
* @date 创建时间：2018年9月6日 下午1:25:13
* 
*/
package com.tmall.common.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
* 用于记录日志
* @author joker 
 * @param <T>
* @date 创建时间：2018年9月6日 下午1:25:13
*/
@Data
public class UserRequestDTO
{
	private UserDTO user;
	
	private Map<String, Object>extProps;
	public UserRequestDTO()
	{
		this.extProps=new HashMap<String, Object>();
	}
	
	

}
