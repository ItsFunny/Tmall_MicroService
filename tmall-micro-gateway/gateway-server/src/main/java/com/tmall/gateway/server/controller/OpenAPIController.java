/**
*
* @author joker 
* @date 创建时间：2018年8月28日 下午12:32:20
* 
*/
package com.tmall.gateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.server.facade.service.IFacadedService;

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

}
