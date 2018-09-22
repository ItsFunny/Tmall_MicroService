/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 上午10:33:12
* 
*/
package com.tmall.server.product.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
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
	
	
	PageResponseDTO<List<PropertyDTO>>findByCondition(PageRequestDTO pageRequestDTO);

}
