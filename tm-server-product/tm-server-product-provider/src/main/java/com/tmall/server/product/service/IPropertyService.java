/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:12
* 
*/
package com.tmall.server.product.service;

import java.util.List;
import java.util.Map;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

/**
* 
* @When
* @Description 显示所有的propertiy
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:12
*/
public interface IPropertyService
{
	//添加属性和值,二合一
	ResultDTO<?>addPropertyAndValue(UserRecordAspectWrapper<PropertyDTO>wrapper);
	
	
	/*
	 * 更新属性,更新属性的时候的参数为:
	 * 属性主体:propertyDTO ,添加的值和更新的值设置在values即可,通过sql 的onduplicate来插入或者更新
	 * 添加的value值:List<ProeprtyValueDTO>
	 * 删除的valueId list
	 */
	ResultDTO<?> updateProperty(UserRecordAspectWrapper<PropertyDTO> wrapper,List<Long>deleteIds);
	
	PageResponseDTO<List<PropertyDTO>>findByCondition(PageRequestDTO pageRequestDTO);
	
	ResultDTO<PropertyDTO>showPropertyValues(Integer propertyId);

}
