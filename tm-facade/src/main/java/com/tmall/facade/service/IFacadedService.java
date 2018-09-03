/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午9:40:16
* 
*/
package com.tmall.facade.service;

import java.util.List;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;


/**
* 聚拢微服务
* @author joker 
* @date 创建时间：2018年8月28日 上午9:40:16
*/
public interface IFacadedService
{
	ResultDTO<String>loginAndAuth(String loginKey,String password,String storeAbbName);
	
	
	
	/*
	 * store
	 */
	ResultDTO<StoreDetail>findStoreDetail(Long storeId);
	ResultDTO<StoreDTO>findStoreByAbbName(String abbName);
	ResultDTO<PageResponseDTO<List<StoreDTO>>>findStoresByPage( PageRequestDTO pageRequestDTO);
	ResultDTO<String>updateStoreStatus(Long storeId,Integer storeStatus);
	
	/*
	 * brand
	 */
	ResultDTO<PageResponseDTO<List<BrandDTO>>>findBrandsByPage(PageRequestDTO pageRequestDTO);	
	
}
