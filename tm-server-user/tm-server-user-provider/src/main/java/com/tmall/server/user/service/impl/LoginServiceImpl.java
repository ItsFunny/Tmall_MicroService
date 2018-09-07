/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午9:46:12
* 
*/
package com.tmall.server.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.Criteria;
import com.tmall.server.user.common.model.TmallEmailLogin;
import com.tmall.server.user.common.model.TmallEmailLoginExample;
import com.tmall.server.user.common.model.TmallMobileLogin;
import com.tmall.server.user.common.model.TmallMobileLoginExample;
import com.tmall.server.user.dao.db1.TEmailLoginDao;
import com.tmall.server.user.dao.db1.TMobileLoginDao;
import com.tmall.server.user.service.ILoginService;


/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月29日 下午9:46:12
 */
@Service
public class LoginServiceImpl implements ILoginService
{
	@Autowired
	private TEmailLoginDao emailLoginDao;
	@Autowired
	private TMobileLoginDao mobileLoginDao;

	@Override
	public TmallEmailLogin loginByEmail(String email)
	{
		TmallEmailLoginExample example = new TmallEmailLoginExample();
	com.tmall.server.user.common.model.TmallEmailLoginExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(email);
		List<TmallEmailLogin> list = emailLoginDao.selectByExample(example);
		if (null == list || list.isEmpty())
		{
			return null;
		} else if (list.size() > 1)
		{
			throw new RuntimeException("find multi users");
		} else
		{
			return list.iterator().next();
		}
	}

	@Override
	public TmallMobileLogin loginByMobile(String mobile)
	{
		TmallMobileLoginExample example = new TmallMobileLoginExample();
		 com.tmall.server.user.common.model.TmallMobileLoginExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<TmallMobileLogin> list = mobileLoginDao.selectByExample(example);
		if (null == list || list.isEmpty())
		{
			return null;
		} else if (list.size() > 1)
		{
			throw new RuntimeException("find multi users");
		} else
		{
			return list.iterator().next();
		}
	}

}
