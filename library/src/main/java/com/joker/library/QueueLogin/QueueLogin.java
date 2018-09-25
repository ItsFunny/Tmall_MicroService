/**
*
* @author joker 
* @date 创建时间：2018年1月23日 下午12:40:21
* 
*/
package com.joker.library.QueueLogin;

/**
*
* @author joker 
* @date 创建时间：2018年1月23日 下午12:40:21
* 功能:
* 	当人数未达到最大在线人数时候,发挥框架自带的并发功能登陆
* 	达到上限之后,单线程塞入等待队列中,等待人数过多抛出异常
* 	后台等待一个线程,等待队列不为空的时候取得并且等待在线队列有多余的空间
*/
public class QueueLogin
{
	
}
