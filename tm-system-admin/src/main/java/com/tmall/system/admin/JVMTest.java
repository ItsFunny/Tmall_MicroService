/**
*
* @author joker 
* @date 创建时间：2018年9月1日 上午7:55:22
* 
*/
package com.tmall.system.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月1日 上午7:55:22
 */
public class JVMTest
{
	public static void createBusyThread()
	{
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
					;

			}
		}, "testBusyThread");
		thread.start();
	}

	/*
	 * 线程等待演示:
	 */
	public static void createLockThread(final Object lock)
	{
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					lock.wait();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, "testLockThread");
		thread.start();
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();
		createBusyThread();
		reader.readLine();
		Object object=new Object();
		createLockThread(object);
	}
}
