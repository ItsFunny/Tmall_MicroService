/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月17日 下午6:25:28
* 
*/
package com.tmall.system.sso.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月17日 下午6:25:28
*/
@Data
public class FormUserModel
{	
	@NotNull
	private String loginKey;

	@NotNull
	private String password;
	
	private long invalidTime;
}
