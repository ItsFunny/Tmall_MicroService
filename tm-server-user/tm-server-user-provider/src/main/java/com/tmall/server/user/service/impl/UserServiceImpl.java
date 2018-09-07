/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午6:55:12
* 
*/
package com.tmall.server.user.service.impl;


import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tmall.common.db.ExtentionResult;
import com.tmall.common.db.MySQLExtention;
import com.tmall.common.dto.UserDTO;
import com.tmall.server.user.common.model.TmallUser;
import com.tmall.server.user.dao.db1.TUserDao;
import com.tmall.server.user.dao.db1.UserDao;
import com.tmall.server.user.service.IUserService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月13日 下午6:55:12
 */
@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private MySQLExtention mySQLExtention;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TUserDao tuserDao;

	@Autowired
	private QueryRunner queryRunner;

	@Override
	public void updateUser(UserDTO userDTO) throws SQLException
	{

		// destination: update user
		/*
		 * 1.先从数据库中通过id查询获得此用户 2.new 一个新的对象(这个对象失去更新的)
		 * 3.传入的对象(newUser)和查找得到的对象(oldUser)进行比较,绝对哪些是需要进行更新的
		 * 4.如果更新的是特殊字段(如邮箱)则需要通过邮箱查询是否已被使用,(若没被使用则需要重新设置状态)
		 * 总而言之,更新的时候是需要将新老对象进行校验的,并且这里最好引入自由度高的queryRunner
		 */
		if (userDTO.getUserId() == null || userDTO.getUserId() < 0)
		{
			throw new IllegalArgumentException("the userId is requeired");
		}
		if (StringUtils.isEmpty(userDTO.getEmail()))
		{
			throw new IllegalArgumentException("the email is required");
		}
		Integer tableNum = getTableNum(userDTO.getUserId());
		userDTO.setTableNum(tableNum);
		UserDTO dbUser = findById(userDTO.getUserId());
		// 这里的else有必要写吗
		if (!userDTO.getUsername().equals(dbUser.getUsername()))
		{
			checkExist("username", userDTO.getUserId(), userDTO.getUsername());
		}
		// else {
		// userDTO.setUsername(null);
		// }
		if (!userDTO.getEmail().equals(dbUser.getEmail()))
		{
			checkExist("email", userDTO.getUserId(), userDTO.getEmail());
		}
		// else {
		// userDTO.setEmail(null);
		// }
		if (!userDTO.getMobile().equals(dbUser.getMobile()))
		{
			checkExist("mobile", userDTO.getUserId(), userDTO.getMobile());
		}
		// else {
		// userDTO.setMobile(null);
		// } 
		//微信的解除绑定单独一个逻辑处理 
//		if (!StringUtils.isEmpty(userDTO.getOpenid()) && !StringUtils.isEmpty(dbUser.getOpenid())
//				&& !userDTO.getOpenid().equals(dbUser.getOpenid()))
//		{
//			checkExist("openid", userDTO.getUserId(), userDTO.getOpenid());
//		}
		userDao.updateUserInfo(userDTO);
	}

	private void checkExist(String key, Long userId, Object param) throws SQLException
	{
		Object num = queryRunner.query("select 1 from tmall_user? where " + key + "=?", new ScalarHandler<>(),
				getTableNum(userId), param);
		if (null != num)
		{
			if (((Number) num).longValue() > 1)
			{
				throw new RuntimeException("sorry ,the " + key + " has been registered ,change it please");
			}
		}
	}

	@Override
	public UserDTO findById(Long userId)
	{
		Integer tableNum = getTableNum(userId);
		UserDTO userDTO = userDao.findById(tableNum, userId);
		return userDTO;
	}



	@Override
	public boolean updateUserPassword(String newPassword, String oldPassword, Long userId)
	{
		Integer tableNum = getTableNum(userId);
		int count = userDao.updateUserPassword(tableNum, newPassword, oldPassword, userId);
		return count>0;
	}
	private Integer getTableNum(Long userId)
	{
		ExtentionResult extentionResult = mySQLExtention.getTableNum("user", userId);
		if(extentionResult==null)
		{
			return null;
		}
		return extentionResult.getTableNum();
	}

	@Override
	public TmallUser findUserByUserId(Long userId)
	{
		Integer tableNum = getTableNum(userId);
//		if(null==tableNum)
//		{
//			tableNum=null;
//		}
		TmallUser user = tuserDao.selectByPrimaryKey(userId, tableNum);
		return user;
	}

}
