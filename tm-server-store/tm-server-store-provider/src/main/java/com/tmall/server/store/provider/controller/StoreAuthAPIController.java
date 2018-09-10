/**
*
* @author joker 
* @date 创建时间：2018年9月1日 下午4:22:26
* 
*/
package com.tmall.server.store.provider.controller;

import java.util.List;

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
 * @date 创建时间：2018年9月1日 下午4:22:26
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth/store")
public class StoreAuthAPIController
{
	@Autowired
	private IStoreService storeService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserFeignService userFeignService;

	/*
	 * 显示店铺详情
	 */
	@RequestMapping(value = "/detail/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<StoreDetail> showStoreDetail(@PathVariable("storeId") Long storeId)
	{
		log.info("[showStoreDetail]显示店铺详细信息");
		StoreDetail storeDetail = new StoreDetail();
		TmallStore store = storeService.findByStoreId(storeId);
		if (null == store)
		{
			throw new TmallStoreException(ErrorCodeEnum.STORE_NOT_EXIST_3002);
		}
		storeDetail.setStore(store.to());
		// 公司信息
		Long companyId = store.getCompanyId();
		TmallCompany company = companyService.findByCompnayId(companyId);
		storeDetail.setCompanyInfo(company.to());
		
		// 联系人信息
		Long userId = store.getUserId();
		//
		try
		{
			ResultDTO<UserDTO> userFeignRes = userFeignService.findByUserId(userId);
			storeDetail.setUserInfo(userFeignRes.getData());
			return ResultUtils.sucess(storeDetail);
		} catch (Exception e)
		{
			throw new TmallStoreException(ErrorCodeEnum.STORE_INTERNAL_SERVER_3001, e);
		}
	}

	/*
	 * 查询所有的店铺
	 */
	@RequestMapping(value = "/all", method =
	{ RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<StoreDTO>>> findAllStores(@RequestBody PageRequestDTO pageRequestDTO)
	{
		// 1.查询数量
		// 2.如果数量大于1则继续查询,如果数量小于1直接返回
		log.info("[findAllStores] 分页查询所有的店铺:pageRequestDTO:{}", pageRequestDTO);
		PageResponseDTO<List<StoreDTO>> response = storeService.findByPage(pageRequestDTO.getPageSize(),
				pageRequestDTO.getPageNum(), pageRequestDTO.getData());
		return ResultUtils.sucess(response);
	}
	/*
	 * 更新店铺状态
	 */
	@RequestMapping(value="/updateStatus/{storeId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String>updateStoreStatus(@PathVariable("storeId")Long storeId,@RequestParam("storeStatus")Integer storeStatus)
	{
		log.info("[updateStoreStatus],更新店铺id为{}的状态为:{}",storeId,storeStatus);
		Integer validCount = storeService.updateStoreStatusByStoreId(storeId, storeStatus);
		if(validCount>0)
		{
			return ResultUtils.sucess();
		}else {
			return ResultUtils.fail("更新失败,故障未知,获取店铺不存在");
		}
	}
}
