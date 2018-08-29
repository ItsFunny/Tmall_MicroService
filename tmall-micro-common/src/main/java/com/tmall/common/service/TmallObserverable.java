/**
*
* @author joker 
* @date 创建时间：2018年8月29日 上午10:16:54
* 
*/
package com.tmall.common.service;

import java.util.Date;
import java.util.Observable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.joker.library.utils.CommonUtils;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserOperationRecord;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月29日 上午10:16:54
 */
public class TmallObserverable extends Observable
{
	public void changedAndNotify(UserDTO user, String detail)
	{
		UserOperationRecord userOperationRecord = new UserOperationRecord();
		userOperationRecord.setDetail(detail);
		userOperationRecord.setUserId(user.getUserId());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (null == attributes)
		{
			userOperationRecord.setIp("unknown");
		} else
		{
			HttpServletRequest request = attributes.getRequest();
			if (null != request)
			{
				String ip = CommonUtils.getIpAddr(request);
				userOperationRecord.setIp(ip);
			} else
			{
				userOperationRecord.setIp("unknown");
			}
		}
		userOperationRecord.setRealname(user.getRealname());
		userOperationRecord.setRecordDate(new Date());
		setChanged();
		notifyObservers(userOperationRecord);
	}

}
