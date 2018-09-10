/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午11:03:39
* 
*/
package com.tmall.server.spi.gateway.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.server.spi.gateway.IGatewayBrandFeignService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 上午11:03:39
 */
@Slf4j
@Component
public class GatewayBrandFallbackServce implements IGatewayBrandFeignService
{
	@Override
	public ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(PageRequestDTO pageRequestDTO)
	{
		log.error("[findBrandsByPage]gateway 触发了服务降级,pageRequestDTO:{}", pageRequestDTO);
		return com.joker.library.utils.ResultUtils.fail("通过gateway获取服务失败,可能gateway宕机了");
	}

}
