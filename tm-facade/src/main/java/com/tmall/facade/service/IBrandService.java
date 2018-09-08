/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:10
* 
*/
package com.tmall.facade.service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午12:04:10
*/
public interface IBrandService
{
	ResultDTO<BrandDTO>findBrandTypeById(Integer brandTypeId);
}
