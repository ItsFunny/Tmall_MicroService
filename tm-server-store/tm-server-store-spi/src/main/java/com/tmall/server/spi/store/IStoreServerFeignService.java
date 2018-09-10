/**
*
* @author joker 
* @date 创建时间：2018年8月27日 下午1:24:25
* 
*/
package com.tmall.server.spi.store;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.server.spi.store.impl.StoreServerFeignFallback;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月27日 下午1:24:25
 */
@FeignClient(name = "store", fallback = StoreServerFeignFallback.class)
public interface IStoreServerFeignService
{
	@RequestMapping(value = "/auth/store/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<StoreDTO>>> findStoresByPage(@RequestBody PageRequestDTO pageRequestDTO);

	@RequestMapping(value = "/auth/store/detail/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<StoreDetail> findStoreDetail(@PathVariable("storeId") Long storeId);

	@RequestMapping(value = "/open/store/{storeAbbName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<StoreDTO> findStoreByAbbName(@PathVariable("storeAbbName") String storeAbbName);

	@RequestMapping(value = "/open/store/test", method = RequestMethod.GET)
	ResultDTO<String> test();

	@RequestMapping(value = "/auth/store/updateStatus/{storeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String> updateStoreStatusByStoreId(@PathVariable("storeId") Long storeId,
			@RequestParam("storeStatus") Integer storeStatus);

	/*
	 * brand
	 */
	@RequestMapping(value = "/auth/brand/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(@RequestBody PageRequestDTO pageRequestDTO);
	
	@GetMapping(value="/auth/brand/{brandId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<BrandDTO>findBrandTypeById(@PathVariable("brandId")Integer brandId);

	// 添加品牌
	@PostMapping(value = "/auth/brand/add",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResultDTO<String> addBrand(@RequestBody UserRequestDTO userRequestDTO);
}
