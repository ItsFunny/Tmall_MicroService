/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:26:10
* 
*/
package com.tmall.common.dto;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 下午6:26:10
 */
public class PropertyDTO
{
	private Integer propertyId;
	private String propertyName;
	private String propertyValue;
	
	
	public Integer getPropertyId()
	{
		return propertyId;
	}

	public void setPropertyId(Integer propertyId)
	{
		this.propertyId = propertyId;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public String getPropertyValue()
	{
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString()
	{
		return "PropertyDTO [propertyId=" + propertyId + ", propertyName=" + propertyName + ", propertyValue="
				+ propertyValue + "]";
	}

}
