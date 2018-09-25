/**
*
* @author joker 
* @date 创建时间：2018年2月2日 下午4:48:38
* 
*/
package com.joker.library.QueueLogin.service.impl;

import com.joker.library.QueueLogin.AbstractLoginServiceImpl;
import com.joker.library.QueueLogin.QueueConfig;
import com.joker.library.QueueLogin.Exception.QueueLoginRangeException;
import com.joker.library.QueueLogin.model.QueueLoginResultInfo;

/**
* 
* @author joker 
 * @param <T>
* @date 创建时间：2018年2月2日 下午4:48:38
*/
@SuppressWarnings("rawtypes")
public class QueueLoginServiceImpl extends AbstractLoginServiceImpl
{
	@SuppressWarnings("unchecked")
	@Override
	public <T> QueueLoginResultInfo login(Object key, T t) throws QueueLoginRangeException
	{
		return super.enter(key, t);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> void setQueueConfig(QueueConfig<T> config)
	{
		super.config=config;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> QueueConfig<T> getQueueConfig()
	{
		return super.config;
	}







	


}
