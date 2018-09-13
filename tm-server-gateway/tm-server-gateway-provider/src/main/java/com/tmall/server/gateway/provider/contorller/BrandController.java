/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午10:39:49
* 
*/
package com.tmall.server.gateway.provider.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IFacadedService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 上午10:39:49
 */
@RestController
@RequestMapping("/valid/brand")
public class BrandController
{
	@Autowired
	private IFacadedService facdedService;

	@RequestMapping(value = "/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPage(@RequestBody PageRequestDTO pageRequestDTO)
	{
		return facdedService.findBrandsByPage(pageRequestDTO);
	}

	/*
	 * 添加品牌
	 */
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> addBrand(@RequestBody UserRequestDTO userRequestDTO)
	{
		return facdedService.addBrand(userRequestDTO);
	}

	/*
	 * 通过id查询品牌
	 */
	@GetMapping(value = "/{brandId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<BrandDTO> findBrandTypeById(@PathVariable("brandId") Integer brandId)
	{
		return facdedService.findBrandTypeById(brandId);
	}

	/*
	 * 批量删除品牌,也可以一个一个删
	 */
	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> deleteBrandsInBatch(@RequestBody UserRequestDTO userRequestDTO)
	{
		return facdedService.deleteBrandsInBatch(userRequestDTO);
	}
}
