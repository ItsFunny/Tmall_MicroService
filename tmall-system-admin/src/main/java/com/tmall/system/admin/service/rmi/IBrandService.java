/**
*
* @author joker 
* @date 创建时间：2018年8月17日 下午10:36:10
* 
*/
package com.tmall.system.admin.service.rmi;

import java.util.Collection;

import com.tmall.common.dto.BrandDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月17日 下午10:36:10
*/

public interface IBrandService
{
	Collection<BrandDTO> findAllBrands();
	
	Collection<BrandDTO>findStoreOperatedBrands(Long storeId);
}
