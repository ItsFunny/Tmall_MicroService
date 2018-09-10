/**
*
* @author joker 
* @date 创建时间：2018年8月20日 下午5:09:15
* 
*/
package com.tmall.server.store.provider.service;

import java.util.List;
import java.util.Map;

import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.service.ObservableService;
import com.tmall.server.store.common.model.TmallStore;

/**
* 
* @author joker 
* @date 创建时间：2018年8月20日 下午5:09:15
*/
public interface IStoreService extends ObservableService
{
	TmallStore findByStoreAbbName(String storeName);
	
	TmallStore findByStoreId(Long storeId);
	
	Long countStores(Map<String, Object>params);
	
	PageResponseDTO<List<StoreDTO>>findByPage(int pageSize,int pageNum,Map<String, Object>conditions);
	
	Integer updateStoreStatusByStoreId(Long storeId,Integer status);
}
