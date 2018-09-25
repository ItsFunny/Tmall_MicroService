/**
*
* @author joker 
* @date 创建时间：2018年2月2日 下午3:54:17
* 
*/
package com.joker.library.QueueLogin.service;

import com.joker.library.QueueLogin.QueueConfig;
import com.joker.library.QueueLogin.Exception.QueueLoginRangeException;
import com.joker.library.QueueLogin.model.QueueLoginResultInfo;

/**
 * 
 * @author joker
 * @date 创建时间：2018年2月2日 下午3:54:17
 */
public interface QueueLoginService
{
	Integer SUCESS=1;
	Integer WAITLOGIN=2;
	<T> QueueLoginResultInfo login(Object key, T t) throws QueueLoginRangeException;

	<T> void setQueueConfig(QueueConfig<T> config);

	<T> QueueConfig<T> getQueueConfig();

}
