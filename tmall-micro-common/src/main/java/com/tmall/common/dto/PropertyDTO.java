/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:26:10
* 
*/
package com.tmall.common.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 下午6:26:10
 */
@Data
public class PropertyDTO
{
	private Integer propertyId;
	private String propertyName;
	private List<PropertyValueDTO>values;
	
	@Data
	public static class PropertyValueDTO
	{
		private Integer propertyValueId;
		private String propertyValue;
	}
	

}
