/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午11:12:26
* 
*/
package com.tmall.facade.service;


import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.StoreDetail;

/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 上午11:12:26
*/
public interface IStoreServiceAdapter
{
	ResultDTO<StoreDetail>findStoreDetail(Long storeId);
	//只有1行代码的话直接给facaded,而不是自行处理
//	ResultDTO<StoreDTO>findStoreByStoreAbbName(String storeAbbName);
	
	
	
	
	
}
