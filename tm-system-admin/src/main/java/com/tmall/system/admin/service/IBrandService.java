/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午1:59:20
* 
*/
package com.tmall.system.admin.service;

import com.joker.library.dto.ResultDTO;
import com.tmall.system.admin.model.BrandFormModel;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 下午1:59:20
*/
public interface IBrandService
{
	ResultDTO<String> addOrUpdateBrand(BrandFormModel formModel);
}
