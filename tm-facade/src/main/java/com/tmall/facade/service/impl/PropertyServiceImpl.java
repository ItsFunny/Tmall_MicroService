/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午5:39:43
* 
*/
package com.tmall.facade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IPropertyService;
import com.tmall.server.spi.product.IProductServerPropertyFeignService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月22日 下午5:39:43
*/
@Service
public class PropertyServiceImpl implements IPropertyService
{
	@Autowired
	private IProductServerPropertyFeignService propertyFeignServie;
	@Override
	public ResultDTO<?> addPropertyAndValue(UserRequestDTO dto)
	{
		return propertyFeignServie.addPropertyAndValues(dto);
	}
	@Override
	public ResultDTO<PageResponseDTO<List<PropertyDTO>>> findPropertiesByCondition(PageRequestDTO pageRequestDTO)
	{
		return propertyFeignServie.findPropertiesByCondition(pageRequestDTO);
	}

}
