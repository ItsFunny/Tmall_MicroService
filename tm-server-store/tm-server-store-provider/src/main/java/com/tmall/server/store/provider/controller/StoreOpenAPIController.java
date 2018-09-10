/**
*
* @author joker 
* @date 创建时间：2018年8月20日 下午5:24:53
* 
*/
package com.tmall.server.store.provider.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.StoreDetail;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.spi.user.IUserFeignService;
import com.tmall.server.store.common.exception.TmallStoreException;
import com.tmall.server.store.common.model.TmallCompany;
import com.tmall.server.store.common.model.TmallStore;
import com.tmall.server.store.provider.service.ICompanyService;
import com.tmall.server.store.provider.service.IStoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 下午5:24:53
 */
@Slf4j
@RestController
@RequestMapping("/open/store")
public class StoreOpenAPIController
{
	@Autowired
	private IStoreService storeService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IUserFeignService userFeignService;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public ResultDTO<String>test()
	{
		throw new TmallStoreException(ErrorCodeEnum.STORE_INTERNAL_SERVER_3001);
	}
	

	@RequestMapping(value = "/{storeAbbName}", method =
	{ RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<StoreDTO> findStoreByStoreAbbName(@PathVariable String storeAbbName)
	{
		log.info("[findStoreByStoreAbbName] 根据店铺缩写查找店铺 ,缩写名称为:{}",storeAbbName);
		TmallStore store = storeService.findByStoreAbbName(storeAbbName);
		StoreDTO storeDTO = new StoreDTO();
		BeanUtils.copyProperties(store, storeDTO);
		return ResultUtils.sucess(storeDTO);
	}

	
	
}
