/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:10
* 
*/
package com.tmall.facade.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:10
*/
public interface IBrandService
{
	ResultDTO<BrandDTO>findBrandTypeById(Integer brandTypeId);
	
	//批量删除
	ResultDTO<String>deleteBrandsInBatch(UserRequestDTO userRequestDTO);
}
