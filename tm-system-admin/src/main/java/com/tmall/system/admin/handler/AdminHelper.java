/**
*
* @author joker 
* @date 创建时间：2018年8月17日 上午10:34:39
* 
*/
package com.tmall.system.admin.handler;

import java.util.Collection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tmall.common.dto.RoleDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.system.admin.util.TreeUtil;
import com.tmall.system.admin.vo.MenuVO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月17日 上午10:34:39
 */
@Component
public class AdminHelper
{
	@Cacheable(cacheNames="menus",key="#userDTO.userId")
	public Collection<MenuVO> parseUserMenus(UserDTO userDTO)
	{
		RoleDTO roleDTO = userDTO.getSelfRole();
		return TreeUtil.spliceMenus(roleDTO.getMenus());
	}
}
