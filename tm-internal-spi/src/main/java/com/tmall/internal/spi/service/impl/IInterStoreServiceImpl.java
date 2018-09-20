/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午3:15:44
* 
*/
package com.tmall.internal.spi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.internal.spi.service.IInternalStoreService;
import com.tmall.server.spi.open.store.IIntternalStoreServerService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月20日 下午3:15:44
*/
@Service
public class IInterStoreServiceImpl implements IInternalStoreService
{
	@Autowired
	private IIntternalStoreServerService internalStoreService;

	public ResultDTO<List<BrandDTO>> findAllBrands()
	{
		ResultDTO<List<BrandDTO>> res = internalStoreService.findAllBrands();
		return res;
	}
	
	
}
