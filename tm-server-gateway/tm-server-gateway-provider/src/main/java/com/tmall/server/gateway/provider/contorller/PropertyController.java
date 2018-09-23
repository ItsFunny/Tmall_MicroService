/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月22日 下午5:41:47
* 
*/
package com.tmall.server.gateway.provider.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.facade.service.IFacadedService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月22日 下午5:41:47
 */
@RestController
@RequestMapping(value = "/valid/property")
public class PropertyController
{
	@Autowired
	private IFacadedService facadService;

	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?> addProperty(@RequestBody UserRequestDTO dto)
	{
		return facadService.addPropertyAndValue(dto);
	}
	@PostMapping(value="/show",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<PropertyDTO>>>showPropertiesByCondition(@RequestBody PageRequestDTO pageRequestDTO)
	{
		return facadService.findProertiesByCondition(pageRequestDTO);
	}

}
