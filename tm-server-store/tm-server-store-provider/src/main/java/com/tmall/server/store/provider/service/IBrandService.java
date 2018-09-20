/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午4:41:13
* 
*/
package com.tmall.server.store.provider.service;

import java.util.List;

import com.tmall.common.dto.BrandDTO;
import com.tmall.common.service.PageBaseService;
import com.tmall.server.store.common.model.TmallBrand;

/**
* 
* @author joker 
* @date 创建时间：2018年9月2日 下午4:41:13
*/
public interface IBrandService extends PageBaseService<List<BrandDTO>>
{
	//这些参数是为了传递
	String USER_REQUEST_BRANDDTO="brandDTO";
	
	
	
	Integer add(TmallBrand brand);
	
	Integer updateSelective(TmallBrand brand);
	
	Integer deleteById(Integer brandId);
	
	Integer deleteByIdInBatch(List<Integer>idList);
	
	
	BrandDTO findBrandTypeById(Integer brandId);
	
	TmallBrand findById(Integer brandId);
	
	List<BrandDTO>findAll();
}
