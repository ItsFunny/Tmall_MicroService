/**
*
* @author joker 
* @date 创建时间：2018年8月28日 下午12:32:20
* 
*/
package com.tmall.server.gateway.provider.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.facade.service.IFacadedService;

/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 下午12:32:20
*/
@RestController
@RequestMapping(value="/open")
public class OpenAPIController
{
	@Autowired
	private IFacadedService facadedService;
	
	@RequestMapping(value="/store/{storeAbbName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<StoreDTO> findStoreByAbbName(@PathVariable("storeAbbName")String storeAbbName)
	{
		return facadedService.findStoreByAbbName(storeAbbName);
	}
//	@RequestMapping(value="/store/all/show",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResultDTO<PageResponseDTO<List<StoreDTO>>>showStoresByPage(@RequestBody PageRequestDTO pageRequestDTO)
//	{
//		return facadedService.findStoresByPage(pageRequestDTO);
//	}

}
