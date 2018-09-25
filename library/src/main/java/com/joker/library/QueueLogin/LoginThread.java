///**
//*
//* @author joker 
//* @date 创建时间：2018年1月24日 上午11:54:25
//* 
//*/
//package com.joker.library.QueueLogin;
//
//
//import com.joker.library.QueueLogin.model.BaseObject;
//
///**
// *
// * @author joker
// * @param <T>
// * @date 创建时间：2018年1月24日 上午11:54:25
// * 
// */
//public class LoginThread<T extends BaseObject> implements Runnable
//{
//	private QueueConfig<T> config;
//	public void run()
//	{
//		while(!Thread.interrupted())
//		{
//			
//		}
//	}
//	public void enter(T t)
//	{
//		if(config.getWaitUserKeyQueue().isEmpty()&&config.getWaitUserKeyQueue().size()==0)
//		{
//			//进入登陆队列
//			enterOnLinueUserQueue(t);
//		}else {
//			//进入等待登陆队列
//			enterWaitLoginQueue(t);
//		}
//	}
//	public void enterOnLinueUserQueue(T t)
//	{
//		Integer size = config.getQueueSize(config.getOnLineUserQueue());
//		if (size < config.getMaxOnlineUserCount())
//		{
//			boolean offer = config.getOnLineUserQueue().offer(t);
//			if (offer)
//			{
//				System.out.println(t + "登陆成功");
//				// 122:这里好像还得需要判断,不过我还没写后台线程:等待队列自动登陆的功能,还没明确好
//				// 18:09 这里不要使用阻塞的方法,1.是因为阻塞下一步不知道该如何传递结果
//				// 2.是阻塞的话如果不控制和后台等待登陆的线程,则会发生抢占,控制的话又会极大浪费性能
//				// PortalUserConfig.OnLineUserQueue.offer(userDTO);
////				setSessionAttribute(userDTO);
//			} else
//			{
//				System.out.println("登陆失败,调入等待队列");
//			}
//		} else
//		{
//			enterWaitLoginQueue(t);
//		}
//	}
//	public void enterWaitLoginQueue(T t)
//	{
//		if (config.getWaitUserKeyQueue().contains(t.getId()))
//		{
//			System.out.println("用户已在等待队列中,删除之前的key");
//			config.getWaitUserKeyQueue().remove(t.getId());
//		}
//		Integer size = config.getQueueSize(config.getWaitUserKeyQueue());
//		if (size < config.getMaxWaitUserCount())
//		{
//			boolean offer = config.getWaitUserKeyQueue().offer(t.getId());
//			try
//			{
//				if (offer)
//				{
//					QueueConfig.isBackThreadRun = true;
//					System.out.println("进入排队等待登陆队列");
//					config.getWaitUserMap().put(t.getId(), t);
////					List<T> list = CommonUtils.collection2List(config.waitUserKeyQueue,
////							t.class);
////					int index = list.indexOf(t);
////					return new ResultVo<>(200, "sucess", index);
//				} else
//				{
//					System.out.println("登陆失败");
////					throw new TmallPortalException(ResultEnums.USER_WAIT_LOGIN_OUT_OF_RANGE);
//				}
//			} finally
//			{
//				// synchronized (QueueConfig.lock)
//				// {
//				// QueueConfig.lock.notifyAll();
//				// }
//			}
//		} else
//		{
////			throw new TmallPortalException(ResultEnums.USER_WAIT_LOGIN_OUT_OF_RANGE);
//		}
//	}
//
//}
