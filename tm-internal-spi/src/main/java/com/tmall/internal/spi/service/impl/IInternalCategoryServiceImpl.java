/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月19日 下午8:28:07
* 
*/
package com.tmall.internal.spi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.internal.spi.service.IInternalCategoryServiece;
import com.tmall.server.spi.open.product.IInternalProductServerCategoryService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月19日 下午8:28:07
*/
@Service
public class IInternalCategoryServiceImpl implements IInternalCategoryServiece
{
	@Autowired
	private IInternalProductServerCategoryService productServerCategoryService;

	public ResultDTO<?> updateCategoryStatus(UserRequestDTO dto)
	{
		return productServerCategoryService.updateCategoryStatus(dto);
	}

}
