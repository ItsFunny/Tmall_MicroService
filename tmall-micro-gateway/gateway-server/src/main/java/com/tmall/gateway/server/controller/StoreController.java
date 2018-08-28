/**
*
* @author joker 
* @date 创建时间：2018年8月28日 下午12:31:51
* 
*/
package com.tmall.gateway.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.server.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月28日 下午12:31:51
 */
@RestController
@RequestMapping("/auth/store")
public class StoreController
{
	@Autowired
	private IFacadedService facadedService;

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<StoreDTO>>> findStoresByPage(@RequestBody PageRequestDTO pageRequestDTO)
	{
		return facadedService.findStoresByPage(pageRequestDTO);
	}
	@RequestMapping(value="/updateStatus/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String>updateStoreStatus(@PathVariable("storeId")Long storeId,@RequestParam("storeStatus")Integer storeStauts)
	{
		return facadedService.updateStoreStatus(storeId, storeStauts);
	}
	
}
