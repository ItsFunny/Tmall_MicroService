/**
*
* @author joker 
* @date 创建时间：2018年2月2日 下午7:43:23
* 
*/
package com.joker.library.QueueLogin;

import com.joker.library.QueueLogin.Exception.QueueLoginRangeException;
import com.joker.library.QueueLogin.service.BackLoginThread;
import com.joker.library.QueueLogin.service.QueueLoginService;
import com.joker.library.QueueLogin.service.impl.QueueLoginServiceImpl;

/**
* 
* @author joker 
* @date 创建时间：2018年2月2日 下午7:43:23
*/
class User
{
	private static Integer id=0;
	private Integer sId=id++;
	protected Integer getsId()
	{
		return sId;
	}
	protected void setsId(Integer sId)
	{
		this.sId = sId;
	}
	@Override
	public String toString()
	{
		return "User [sId=" + sId + "]";
	}
	
	
}
public class Test
{
	public static void main(String[] args) throws QueueLoginRangeException
	{
		QueueLoginService queueLoginService=new QueueLoginServiceImpl();
		QueueConfig<User> generate = QueueConfig.generate(2,5);
		queueLoginService.setQueueConfig(generate);
		QueueConfig<User> queueConfig = queueLoginService.getQueueConfig();
		for(int i=0;i<3;i++)
		{
			Thread thread=new Thread(new BackLoginThread<>(queueConfig));
			thread.start();
		}
		for(int i=0;i<10;i++)
		{
			User user=new User();
			queueLoginService.login(user.getsId(), user);
		}
	}
}	
