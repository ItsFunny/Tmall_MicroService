/**
*
* @author joker 
* @date 创建时间：2018年8月29日 上午10:04:25
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年8月29日 上午10:04:25
*/
@Data
public class UserOperationRecord implements Serializable
{
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月29日 上午10:09:25
	*/
	private static final long serialVersionUID = -3121105730698511825L;
	private Long userId;
	private String realname;
	private Date recordDate;
	private String ip;
	//detail 需要按照特定的格式才行,如 行为概述:userId:userName:被操作的事物:这里可有可无,就是些补充描述
	//如 update:1:吕聪:2,3:更新了storeId为2,3的状态,审核通过
	private String detail;
}
