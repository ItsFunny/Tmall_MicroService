/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午3:15:12
* 
*/
package com.tmall.internal.spi.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 下午3:15:12
*/
public interface IInternalStoreService
{
	ResultDTO<List<BrandDTO>> findAllBrands();

}
