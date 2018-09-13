/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午9:05:15
* 
*/
package com.tmall.server.store.provider.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.mq.AppEventPublisher;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.utils.JsonUtil;
import com.tmall.common.annotation.ArgumentCheck;
import com.tmall.common.config.TmallConfigProperty;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.spi.gateway.message.IGatewayMessageService;
import com.tmall.server.store.common.exception.TmallStoreException;
import com.tmall.server.store.common.model.TmallBrand;
import com.tmall.server.store.provider.service.IBrandService;
import com.tmall.server.store.provider.service.IBrandTransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 上午9:05:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth/brand")
public class BrandAuthAPIController
{
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IGatewayMessageService gatewayMessageService;

	@Autowired
	private TmallConfigProperty configProperty;

	@Autowired
	private IBrandTransactionService brandTransactionService;
	// @Autowired
	// private AppEventPublisher eventPublisher;

	@RequestMapping(value = "/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<BrandDTO>>> findBrandsByPageCondition(
			@RequestBody PageRequestDTO pageRequestDTO)
	{
		PageResponseDTO<List<BrandDTO>> responseDTO = brandService.findByPage(pageRequestDTO);
		ResultDTO<PageResponseDTO<List<BrandDTO>>> resultDTO = ResultUtils.sucess(responseDTO);
		pageRequestDTO = null;
		return resultDTO;
	}

	//感觉这个没必要开放接口,直接在本地服务校验最好
	@RequestMapping(value = "/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<BrandDTO> findById(@PathVariable("brandId") Integer brandId)
	{
		return com.joker.library.utils.ResultUtils.sucess(brandService.findBrandTypeById(brandId));
	}

	// @RequestMapping(value = "/add", consumes =
	// MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	// public ResultDTO<String> addBrand(@RequestBody BrandDTO brandDTO)
	// {
	// TmallBrand tmallBrand = new TmallBrand();
	// tmallBrand.from(brandDTO);
	// try
	// {
	// Integer res = brandService.add(tmallBrand);
	// //发布消息
	// if (res > 0)
	// {
	// return ResultUtils.sucess();
	// } else
	// {
	// return ResultUtils.fail("插入失败,请刷新重试");
	// }
	// } catch (Exception e)
	// {
	// return ResultUtils.fail("名字冲突了");
	// }
	// }

	// 再改,通过aop进行参数校验
	@ArgumentCheck(type = "com.tmall.common.dto.UserRequestDTO")
	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResultDTO<String> addBrand(@RequestBody UserRequestDTO userRequestDTO)
	{
		Map<String, Object> map = userRequestDTO.getExtProps();
		Object brandDtoObj = map.get(IBrandService.USER_REQUEST_BRANDDTO);
		if (null == brandDtoObj)
		{
			throw new TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_MISSING_ARGUMENT, "brandDTO"));
		}
		UserDTO user = userRequestDTO.getUser();
		BrandDTO brandDTO = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			brandDTO = mapper.convertValue(brandDtoObj, BrandDTO.class);
		} catch (Exception e)
		{
			throw new TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_WRONG_CLASS_TYPE,
					BrandDTO.class.getName(), brandDtoObj.getClass().getName()), e);
		}
		String detail = null;
		if(null==brandDTO.getBrandTypeId())
		{
			throw new TmallStoreException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.STORE_MISSING_ARGUMENT, "缺少typeId参数"));
		}
		if (brandDTO.getBrandId() != null)
		{
			TmallBrand brand = brandService.findById(brandDTO.getBrandId());
			if (null == brand)
			{
				throw new TmallStoreException(ErrorCodeEnum.STORE_NO_RECORDS_IN_DB);
			}
			detail = "更新品牌信息,原先品牌信息为" + brand + ",更改为了:" + brandDTO;
		} else
		{
			detail = "增加了一个品牌,信息为:" + brandDTO;
		}
		UserRecordAspectWrapper<BrandDTO> wrapper = new UserRecordAspectWrapper<>(user, detail,
				TmallMQEnum.USER_RECORD.getExchangeName(), TmallMQEnum.USER_RECORD.getRoutinKey(), brandDTO);
		return brandTransactionService.addOrUpdateBrand(wrapper);
	}

	// @RequestMapping(value = "/add", consumes =
	// MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	// public ResultDTO<String> addBrand(@RequestBody BrandDTO brandDTO)
	// {
	// TmallBrand tmallBrand = new TmallBrand();
	// tmallBrand.from(brandDTO);
	// try
	// {
	// Integer res = brandService.add(tmallBrand);
	// //发布消息
	// if (res > 0)
	// {
	// return ResultUtils.sucess();
	// } else
	// {
	// return ResultUtils.fail("插入失败,请刷新重试");
	// }
	// } catch (Exception e)
	// {
	// return ResultUtils.fail("名字冲突了");
	// }
	// }
	
}
