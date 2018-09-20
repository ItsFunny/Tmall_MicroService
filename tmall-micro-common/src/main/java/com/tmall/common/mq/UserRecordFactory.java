/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午4:47:19
* 
*/
package com.tmall.common.mq;

import com.tmall.common.dto.UserDTO;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 下午4:47:19
 */
public class UserRecordFactory
{
	public static <T> UserRecordAspectWrapper<T> create(UserDTO user, String detail, T data)
	{
		return new UserRecordAspectWrapper<T>(user, detail, TmallMQEnum.USER_RECORD.getExchangeName(),
				TmallMQEnum.USER_RECORD.getRoutinKey(), data);
	}
}
