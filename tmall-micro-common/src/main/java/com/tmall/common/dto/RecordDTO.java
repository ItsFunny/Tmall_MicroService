/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午9:18:19
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月4日 上午9:18:19
*/
@Data
public class RecordDTO implements Serializable
{

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月4日 上午9:18:42
	*/
	private static final long serialVersionUID = -5414208934211886135L;
	
	private Long userId;
	
	private String realname;
	
	private Date createDate;
	
	private String detail;
}
