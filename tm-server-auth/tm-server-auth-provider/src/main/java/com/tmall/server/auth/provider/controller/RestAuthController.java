/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月30日 下午7:27:43
* 
*/
package com.tmall.server.auth.provider.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.ActionDTO;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.MenuDTO;
import com.tmall.common.dto.RoleDTO;
import com.tmall.common.dto.StoreDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.CommonStatusEnum;
import com.tmall.common.utils.JWTUtils;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.auth.common.model.TmallRole;
import com.tmall.server.auth.provider.service.IMenuService;
import com.tmall.server.auth.provider.service.IPermissionService;
import com.tmall.server.auth.provider.service.IRoleService;
import com.tmall.server.spi.gateway.store.IGatewayStoreFeignService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月30日 下午7:27:43
 */
@RestController
public class RestAuthController
{
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IGatewayStoreFeignService gateWayStoreFeignService;
	@Autowired
	private JWTUtils jwtUtil;

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "/auth/url/check", method =
	{ RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?> checkUrlAuth(@RequestParam("userId") Long userId, @RequestParam("roleId") Integer roelId,
			@RequestParam("url") String url)
	{

		return ResultUtils.fail();
	}

	// 获取token,用什么换取,用自己的身份证信息,以及storeAppName去换取
	@RequestMapping(value = "/auth/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<String> getAuthToken(@RequestBody String token)
	{
		if (StringUtils.isEmpty(token))
		{
			return ResultUtils.fail("缺少参数:token");
		}
		AuthTokenDTO authRequestDTO = null;
		try
		{
			token = URLDecoder.decode(token, "utf-8");
			authRequestDTO = jwtUtil.parseBySSOPublicKey(token);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return ResultUtils.fail("无效的token");
		}
		if (null == authRequestDTO)
		{
			return ResultUtils.fail("无效的token");
		}
		UserDTO userDTO = authRequestDTO.getData();
		String storeAbbName = authRequestDTO.getStoreAbbName();
		if (null == userDTO || StringUtils.isEmpty(storeAbbName))
		{
			return ResultUtils.fail("错误的内部参数");
		}
		Long userId = userDTO.getUserId();
		ResultDTO<StoreDTO> res = gateWayStoreFeignService.findStoreByStoreAbbName(storeAbbName);
		if (!res.isSuccess())
		{
			return ResultUtils.fail(res.getMsg());
		}
		StoreDTO store = res.getData();
		userDTO.getStores().add(store);
		/*
		 * 1.通过userId和storeId得到roleId 2.通过roleId得到menus和actions 2018-09-02 11:38
		 * 这些不在这里查询,防止token太过巨大
		 */
		TmallRole role = roleService.findRoleByUserIdAndStioreId(userId, store.getStoreId());
		RoleDTO roleDTO = new RoleDTO();
		if (null != role)
		{
			role.to(roleDTO);
			// //查找菜单
			// List<MenuDTO> menus = menuService.findMenusByRoleId(role.getRoleId());
			// //查找权限
			// //这里最好是menudto和actiondto同样继承与同一个基类,这里不做内存筛选,在其后台让其自行处理才好感觉
			// List<ActionDTO> permissions =
			// permissionService.findRolePermissionByRoleId(role.getRoleId(),
			// CommonStatusEnum.enable.ordinal());
			// roleDTO.setMenus(menus);
			// roleDTO.setPermisions(permissions);
		}
		// 这里也需要设置,无角色信息的话怎么办,不过讲道理的话添加用户会自动分配一个默认角色的才对
		userDTO.setSelfRole(roleDTO);
		AuthTokenDTO authTokenDTO = new AuthTokenDTO();
		authTokenDTO.setData(userDTO);
		authRequestDTO.setStoreAbbName(storeAbbName);
		String resToken = null;
		try
		{
			resToken = jwtUtil.buildByAuthPrivateKey(authTokenDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ResultUtils.sucess(resToken, "success");
	}

	/*
	 * 根据用户的roleId查询相关的权限信息
	 */
	@RequestMapping(value = "/auth/checkAndGetActions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<UserDTO> findUserActions(@RequestBody String userToken)
	{
		AuthTokenDTO authTokenDTO = jwtUtil.parseByAuthPublicKey(userToken);
		if (null == authTokenDTO)
		{
			return ResultUtils.fail("无效的token信息");
		}
		if (null == authTokenDTO.getInvalidTime() || System.currentTimeMillis() > authTokenDTO.getInvalidTime())
		{
			return ResultUtils.fail("身份认证信息已过期");
		}
		UserDTO userDTO = authTokenDTO.getData();
		RoleDTO roleDTO = userDTO.getSelfRole();
		// 查找菜单
		List<MenuDTO> menus = menuService.findMenusByRoleId(roleDTO.getRoleId());
		// 查找权限
		// 这里最好是menudto和actiondto同样继承与同一个基类,这里不做内存筛选,在其后台让其自行处理才好感觉
		List<ActionDTO> permissions = permissionService.findRolePermissionByRoleId(roleDTO.getRoleId(),
				CommonStatusEnum.enable.ordinal());
		roleDTO.setMenus(menus);
		roleDTO.setPermisions(permissions);
		return ResultUtils.sucess(userDTO);
	}

	@RequestMapping("/test")
	public ResultDTO<String> test()
	{
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return ResultUtils.sucess();
	}

}
