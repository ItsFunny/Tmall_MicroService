/**
*
* @author joker 
* @date 创建时间：2018年6月24日 上午11:35:22
* 
*/
package com.tmall.server.gateway.service.product.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.joker.library.utils.KeyUtils;
import com.tmall.common.constants.SecurityConstant;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.server.gateway.service.BaseService;
import com.tmall.server.gateway.service.product.IProductServerBrandService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 上午11:35:22
 */
@Service
public class ProductServerBrandServiceImpl extends BaseService implements IProductServerBrandService
{
	private String serverName = "PRODUCT";

	@Override
	public ResultDTO<Collection<BrandDTO>> findAllBrands()
	{
		String url = getServerUrl(serverName);
		String appId = "joker";
		String tm = String.valueOf(System.currentTimeMillis());
		String appSecret = KeyUtils.md5Encrypt(appId + tm+SecurityConstant.AUTH_KEY);
		@SuppressWarnings("unchecked")
		ResultDTO<Collection<BrandDTO>> resultDTO = restTemplate.getForObject(
				url + "/auth/brand/all?appId=" + appId + "&tm=" + tm + "&appSecret=" + appSecret, ResultDTO.class);
		return resultDTO;
	}
}
