/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午2:58:37
* 
*/
package com.tmall.server.store.provider.service;

import java.util.List;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午2:58:38
*/
public interface IBrandTransactionService
{
//	ResultDTO<String> addBrand(UserDTO user,BrandDTO brandDTO);
	
	
	ResultDTO<String> addOrUpdateBrand(UserRecordAspectWrapper<BrandDTO> wrapper);
	
	
	ResultDTO<String> deleteByIdInBatch(UserRecordAspectWrapper<List<Integer>> wrapper);
}
