/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午12:48:20
* 
*/
package com.tmall.server.spi.gateway.store;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.server.spi.gateway.fallback.GatewayStoreFallbackService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午12:48:20
 */
@FeignClient(name="gateway",fallback=GatewayStoreFallbackService.class)
public interface IGatewayStoreFeignService
{
	/*
	 * 测试用的
	 */
//	@RequestMapping(value="/open/store/all/show",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	ResultDTO<PageResponseDTO<List<StoreDTO>>>showAllStores(@RequestBody PageRequestDTO pageRequestDTO);
//	
//	@RequestMapping(value="/auth/store/{storeAbbName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	 ResultDTO<StoreDTO>findAuthStore(@PathVariable("storeAbbName") String storeAbbName);
	/*
	 * 
	 */
	
	@RequestMapping(value="/open/store/{storeAbbName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 ResultDTO<StoreDTO>findStoreByStoreAbbName(@PathVariable("storeAbbName") String storeAbbName);
	
	@RequestMapping(value="/valid/store/all",method= {RequestMethod.POST},produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<StoreDTO>>>findStoresByPage(@RequestBody PageRequestDTO pageRequestDTO);
	
	
	
	@RequestMapping(value="/valid/store/updateStatus/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>updateStoreStatusByStoreId(@PathVariable("storeId")Long storeId,@RequestParam("storeStatus")Integer storeStatus);
}
