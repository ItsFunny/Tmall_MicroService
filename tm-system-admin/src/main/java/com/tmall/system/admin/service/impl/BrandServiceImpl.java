/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午1:59:58
* 
*/
package com.tmall.system.admin.service.impl;

import static org.mockito.Mockito.reset;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joker.library.dto.ResultDTO;
import com.joker.library.mq.AppEvent;
import com.joker.library.mq.AppEventPublisher;
import com.joker.library.utils.JsonUtil;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.MessageDTO;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.mq.TmallMQEnum;
import com.tmall.server.spi.gateway.brand.IGatewayBrandService;
import com.tmall.server.spi.gateway.message.IGatewayMessageService;
import com.tmall.server.store.common.model.MessageModel;
import com.tmall.system.admin.model.BrandFormModel;
import com.tmall.system.admin.service.IBrandService;
import com.tmall.system.admin.util.AdminUtil;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午1:59:58
 */
@Service
public class BrandServiceImpl implements IBrandService
{
	@Autowired
	private IGatewayBrandService gatewayBrandService;
	@Autowired
	private AppEventPublisher eventPublisher;
	@Autowired
	private IGatewayMessageService gatewayMessageService;

	@Transactional(rollbackFor = Exception.class)
	// @Override
	// public ResultDTO<String> addBrand(BrandFormModel formModel)
	// {
	// /*
	// * 1.
	// */
	// BrandDTO brandDTO = new BrandDTO();
	// formModel.to(brandDTO);
	// UserDTO user = AdminUtil.getLoginUser();
	// // 发布消息
	// String detail = "增加了一个品牌:" + formModel;
	// RecordDTO recordDTO = new RecordDTO();
	// recordDTO.setUserId(user.getUserId());
	// recordDTO.setRealname(user.getRealname());
	// recordDTO.setDetail(detail);
	// AppEvent event = new AppEvent(TmallMQEnum.USER_RECORD.getExchangeName(),
	// TmallMQEnum.USER_RECORD.getRoutinKey(), recordDTO);
	// String json = JsonUtil.obj2Json(event);
	// //本地添加消息记录+消息服务添加预备消息
	// Integer validCount = messageService.insert(new MessageModel(event.getUuid(),
	// json));
	// if (validCount <= 0)
	// {
	// return ResultUtils.fail("本地记录操作失败");
	// }else {
	// //更新消息服务状态为ok,可被下游服务订阅
	// }
	// ResultDTO<String> resultDTO = gatewayBrandService.addBrand(brandDTO);
	// return resultDTO;
	// }
	// @Override
	// public ResultDTO<String> addBrand(BrandFormModel formModel)
	// {
	// BrandDTO brandDTO = new BrandDTO();
	// formModel.to(brandDTO);
	// // 1.本地插入记录,插入成功则开始服务调用
	// UserDTO user = AdminUtil.getLoginUser();
	// String detail = "增加了一个品牌:" + formModel;
	// RecordDTO recordDTO = new RecordDTO();
	// recordDTO.setUserId(user.getUserId());
	// recordDTO.setRealname(user.getRealname());
	// recordDTO.setDetail(detail);
	// AppEvent event = new AppEvent(TmallMQEnum.USER_RECORD.getExchangeName(),
	// TmallMQEnum.USER_RECORD.getRoutinKey(),
	// recordDTO);
	// String json = JsonUtil.obj2Json(event);
	// // 本地添加消息记录+消息服务添加预备消息
	// Integer validCount = messageService.insert(new MessageModel(event.getUuid(),
	// json));
	// if (validCount <= 0)
	// {
	// return ResultUtils.fail("本地插入message记录失败");
	// }
	// // 2.服务调用成功,发布消息 ,失败则直接返回结果
	// ResultDTO<String> resultDTO = gatewayBrandService.addBrand(brandDTO);
	// if(!resultDTO.isSuccess())
	// {
	// return ResultUtils.fail(resultDTO.getMsg());
	// }
	// eventPublisher.publish(event);
	// // 3.发布消息成功则删除本地记录(或者更新本地记录为已发送)->在confirmCallBack中编写
	// return resultDTO;
	// }
	// 第三次更改
	@Override
	public ResultDTO<String> addBrand(BrandFormModel formModel)
	{
		Integer typeId = formModel.getBrandType();
		ResultDTO<BrandDTO> typeRes = gatewayBrandService.findTypeByTypeId(typeId);
		if(!typeRes.isSuccess())
		{
			return ResultUtils.fail(typeRes.getMsg());
		}
		BrandDTO data = typeRes.getData();
		if(null==data)
		{
			return ResultUtils.fail("品牌类型不存在");
		}
//		BrandDTO brandDTO = new BrandDTO();
		formModel.to(data);
//		brandDTO.setBrandTypeId(data.getBrandTypeId());
//		brandDTO.setBrandTypeName(data.getBrandName());
		UserDTO user = AdminUtil.getLoginUser();
		UserRequestDTO userRequestDTO=new UserRequestDTO();
		Map<String, Object>extProps=new HashMap<String, Object>();
		extProps.put("brandDTO", data);
		userRequestDTO.setUser(user);
		userRequestDTO.setExtProps(extProps);
		// 调用服务
		ResultDTO<String> resultDTO = gatewayBrandService.addBrand(userRequestDTO);
		//直接返回即可
		return resultDTO;
	}

}
