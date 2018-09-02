/**
*
* @author joker 
* @date 创建时间：2018年9月1日 上午9:40:46
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
* 店铺详情信息
* @author joker 
* @date 创建时间：2018年9月1日 上午9:40:46
*/
@Data
public class StoreDetail implements Serializable
{
/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月1日 上午9:43:06
	*/
	private static final long serialVersionUID = -4332521557320499242L;
	
	private StoreDTO store;
	private UserDTO userInfo;
	private CompanyDTO companyInfo;
	
}
