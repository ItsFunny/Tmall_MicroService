/**
*
* @author joker 
* @date 创建时间：2018年8月20日 下午5:46:29
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 下午5:46:29
 */
@Data
public class StoreDTO implements Serializable
{

	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年8月20日 下午5:46:42
	 */
	private static final long serialVersionUID = -4204142058466052726L;

	private Long storeId;

	private String storeName;

	private String storeAbbName;

	private String storeDescription;

	private Integer storeStatus;

	private String storeContactPhone;

	private Long userId;

	private String storeContactName;

	private Long companyId;

	private String companyName;
	
	


}
