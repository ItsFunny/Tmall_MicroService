/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午5:38:04
* 
*/
package com.tmall.facade.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月22日 下午5:38:04
*/
public interface IPropertyService
{
	ResultDTO<?>addPropertyAndValue(UserRequestDTO dto);
	
	
	ResultDTO<PageResponseDTO<List<PropertyDTO>>>findPropertiesByCondition(PageRequestDTO pageRequestDTO);
	
}
