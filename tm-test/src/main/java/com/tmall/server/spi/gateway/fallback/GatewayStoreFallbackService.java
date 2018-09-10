/**
*
* @author joker 
* @date 创建时间：2018年9月1日 下午9:13:52
* 
*/
package com.tmall.server.spi.gateway.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.spi.gateway.store.IGatewayStoreFeignService;

/**
* 
* @author joker 
* @date 创建时间：2018年9月1日 下午9:13:52
*/
@Component
public class GatewayStoreFallbackService implements IGatewayStoreFeignService
{

	@Override
	public ResultDTO<StoreDTO> findStoreByStoreAbbName(String storeAbbName)
	{
		return null;
	}

	@Override
	public ResultDTO<PageResponseDTO<List<StoreDTO>>> findStoresByPage(PageRequestDTO pageRequestDTO)
	{
		return ResultUtils.fail("store服务器可能宕机了,请稍后再试");
	}

	@Override
	public ResultDTO<String> updateStoreStatusByStoreId(Long storeId, Integer storeStatus)
	{
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ResultDTO<PageResponseDTO<List<StoreDTO>>> showAllStores(PageRequestDTO pageRequestDTO)
//	{
//		return null;
//	}
//
//	@Override
//	public ResultDTO<StoreDTO> findAuthStore(String storeAbbName)
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}

}
