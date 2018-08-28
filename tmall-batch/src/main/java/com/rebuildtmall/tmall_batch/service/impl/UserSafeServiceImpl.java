/**
*
* @author joker 
* @date 创建时间：2018年5月16日 下午4:59:59
* 
*/
package com.rebuildtmall.tmall_batch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebuildtmall.tmall_batch.dao.UserAbnormalDao;
import com.rebuildtmall.tmall_batch.model.UserOffsiteRecord;
import com.rebuildtmall.tmall_batch.service.UserSafeService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月16日 下午4:59:59
 */
@Service
public class UserSafeServiceImpl implements UserSafeService
{

	@Autowired
	private UserAbnormalDao userAbnormalDao;

	@Override
	public void recordUserOffsiteRecord(UserOffsiteRecord record)
	{
		userAbnormalDao.addRecord(record);
	}

}
