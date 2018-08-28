/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午12:48:20
* 
*/
package com.tmall.serer.spi.gateway.feign.store;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午12:48:20
 */
@FeignClient("gateway")
public interface IGatewayStoreFeignService
{
	
	@RequestMapping(value="/open/store/{storeAbbName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 ResultDTO<StoreDTO>findStoreByStoreAbbName(@PathVariable("storeAbbName") String storeAbbName);
	
	@RequestMapping(value="/auth/store/all",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<StoreDTO>>>findStoresByPage(@RequestBody PageRequestDTO pageRequestDTO);
	
	@RequestMapping(value="/auth/store/updateStatus/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String>updateStoreStatusByStoreId(@PathVariable("storeId")Long storeId,@RequestParam("storeStatus")Integer storeStatus);
}
