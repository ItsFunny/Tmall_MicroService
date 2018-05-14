/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午9:50:14
* 
*/
package org.login_server.service.impl;

import org.login_server.dao.TypeLoginDao;
import org.login_server.dao.UserDao;
import org.login_server.model.User;
import org.login_server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午9:50:14
 */
@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private UserDao userDao;

	@Autowired
	private TypeLoginDao typeLoginDao;

	@Override
	public User findByEmail(String email)
	{
		String userIdStr = typeLoginDao.findIdByEmail(email);
		if (StringUtils.isEmpty(userIdStr))
		{
			return null;
		}
		Long userId = Long.parseLong(userIdStr);
		long tableNum = userId & 1;
		return userDao.findByEmail((int) tableNum, email);
	}

}
