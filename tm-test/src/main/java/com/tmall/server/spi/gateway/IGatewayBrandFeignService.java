/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午11:01:38
* 
*/
package com.tmall.server.spi.gateway;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 上午11:01:38
 */
@FeignClient(name = "gateway")
public interface IGatewayBrandFeignService
{
	@RequestMapping(value = "/valid/brand/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(@RequestBody PageRequestDTO pageRequestDTO);
}
