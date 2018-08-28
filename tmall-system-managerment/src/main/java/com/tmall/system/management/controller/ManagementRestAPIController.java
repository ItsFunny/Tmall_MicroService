/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午6:29:27
* 
*/
package com.tmall.system.management.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.joker.library.utils.CommonUtils;
import com.tmall.common.constants.DealWayConstant;
import com.tmall.common.constants.RabbitMQExchangeNameConstant;
import com.tmall.common.constants.SafeFileConstant;
import com.tmall.common.dto.ProductDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.PicTypeEnum;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEvent;
import com.tmall.common.event.AppEventPublisher;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.product.service.IProductServerProductService;
import com.tmall.system.management.utils.ManagementUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月25日 下午6:29:27
 */
@RestController
@RequestMapping("/api")
public class ManagementRestAPIController
{
	private Logger logger = LoggerFactory.getLogger(ManagementRestAPIController.class);
	@Autowired
	private IProductServerProductService productService;

	@Autowired
	private AppEventPublisher eventPublisher;

	@RequestMapping("/product/add.do")
	public ResultDTO<Long> doAddProduct(ProductDTO productDTO, HttpServletRequest request, HttpServletResponse response)
	{
		if (null == productDTO)
		{
			return ResultUtils.fail("illegal argument,参数错误");
		}
		UserDTO userDTO = ManagementUtils.getLoginUser();
		logger.info("[add product] user:{} with the role{}: begin add product at {}",
				userDTO.getUserId() + ":" + userDTO.getUsername(), userDTO.getSelfRole().getRoleId(), new Date());
		Long productId = null;
		try
		{
			productId = productService.addOrUpdate(productDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error("[add product] occured exception for reason:{} when {} with the user:{}", e.getCause(),
					new Date(), userDTO.getUserId());
			return ResultUtils.fail(e.getMessage());
		}
		// send a message to queue to show one user has been added a product
		// data:user Id, productId
		AppEvent event = new AppEvent();
		event.setExchangeName(RabbitMQExchangeNameConstant.USER);
		event.setRoutingKey(RabbitMQEnum.USER_DEAL_WITH_PRODUCT.getRoutingKey());
		HashMap<String, Object> data = new HashMap<>();
		data.put("userId", userDTO.getUserId());
		data.put("productId", productId);
		data.put("detail", DealWayConstant.ADD);
		event.setData(data);
		eventPublisher.publish(event);
		return ResultUtils.sucess(productId);
	}
	@RequestMapping("/product/property/safe")
	public ResultDTO<String>safeSpuProperty(HttpServletRequest request,HttpServletResponse response)
	{
		String pros = request.getParameter("pros");
		if(StringUtils.isEmpty(pros))
		{
			return ResultUtils.fail("illegal argument,参数错误");
		}
		if(CommonUtils.validString(pros))
		{
			
		}
		/*
		 * 
		 */
		return null;
	}

	/*
	 * file upload
	 */
	@RequestMapping("/picture/save.do")
	@ResponseBody
	public ResultDTO<String> uploadFile(@RequestParam(name = "pictureFile",required=false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response)
	{
		String picTypeStr = request.getParameter("picType");
		String productIdStr = request.getParameter("productId");
		if (StringUtils.isEmpty(picTypeStr) || StringUtils.isEmpty(productIdStr)||file.isEmpty())
		{
			return ResultUtils.fail("illegal argument,参数错误");
		}
		try
		{
			int picType = Integer.parseInt(picTypeStr);
			Long productId = Long.parseLong(productIdStr);
			String picUrl = saveImg(request, file, productId, picType);
			System.out.println(picUrl);
			return ResultUtils.sucess(picUrl);
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
			return ResultUtils.fail("illegal argument,无法从string 转换为基本数据类型");
		} catch (Exception e)
		{
			e.printStackTrace();
			return ResultUtils.fail("unknown exception:" + e.getCause());
		}
	}

	@RequestMapping("/picture/save.do2")
	public ResultDTO<String> uploadFile2(@RequestParam(name = "pictureFile",required=false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response,@RequestParam Map<String, Object>params)
	{
		System.out.println(file);
		String productId = request.getParameter("productId");
		return ResultUtils.sucess();
	}
	private String saveImg(HttpServletRequest request, MultipartFile file, Long productId, Integer type)
			throws IllegalStateException, IOException
	{
		File dirFile = null;
//		String realPath = Thread.currentThread().getContextClassLoader().getResource("static").getPath()+File.separator+"pics";
		String realPath=SafeFileConstant.PRODUCT_SAFE_ADDRESS;
		System.out.println(file.getOriginalFilename());
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String tempPath = null;
		if (type.equals(PicTypeEnum.SINGLE.ordinal()))
		{
			tempPath = File.separator + "single" + File.separator + productId+File.separator;
		} else if (type.equals(PicTypeEnum.DETAIL.ordinal()))
		{
			tempPath = File.separator + "detail" + File.separator + productId+File.separator;
		} else
		{
			tempPath = File.separator + "description" + File.separator + productId+File.separator;
			// dirFile=new
			// File(realPath+File.separator+"description"+File.separator+productId);
		}
		System.out.println(realPath+tempPath);
		dirFile = new File(realPath + tempPath);
		if (!dirFile.exists())
		{
			dirFile.mkdirs();
		}
		String picId = UUID.randomUUID().toString();

		File newFile = new File(dirFile.getAbsolutePath() + File.separator + picId + suffix);
		String picPath = tempPath + File.separator + picId + suffix;
		file.transferTo(newFile);
		String address = getAddress(request);
		return address + File.separator+"pics"+picPath;
	}

	private String getAddress(HttpServletRequest request)
	{
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		String address = url.substring(0, url.indexOf(uri));
		return address;
	}

	@RequestMapping("/product/add.do2")
	public ResultDTO<Long> doAddProduct2(ProductDTO productDTO, HttpServletRequest request,
			HttpServletResponse response)
	{
		return ResultUtils.sucess(1L);
	}

}
