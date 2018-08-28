/**
*
* @author joker 
* @date 创建时间：2018年8月17日 下午10:36:28
* 
*/
package com.tmall.system.admin.service.rmi.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;

import com.tmall.common.dto.BrandDTO;
import com.tmall.system.admin.service.rmi.IBrandService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月17日 下午10:36:28
*/
//做到的时候再改
@Service
public class BrandServiceImpl implements IBrandService
{
//	@Autowired
//	private IBrandServerFeignService brandServerFeignService;
	

	@Override
	public Collection<BrandDTO> findAllBrands()
	{
		
//		return brandServerFeignService.findAllBrands();
		return null;
	}

	@Override
	public Collection<BrandDTO> findStoreOperatedBrands(Long storeId)
	{
		//因为不需要进行转换,所以不考虑success or not ,直接return
		//当然也可以添加自己的额外措施,失败了的话返回什么
//		ResultDTO<List<BrandDTO>> res = brandServerFeignService.findStoreOperatedBrands(storeId);
//		return res.getData();
		return null;
	}

}
