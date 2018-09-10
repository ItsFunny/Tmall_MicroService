/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午5:33:59
* 
*/
package com.tmall.server.message.holder;

import com.joker.library.mq.AppEvent;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月5日 下午5:33:59
*/
@Data
public class JobWrapper
{
	/*
	 * 之所以会有
	 * ready和
	 * sendSuccess选项是因为:1.字面理解更加符合逻辑
	 * 2.存在这种case,后台线程会挑选所有线程中为ready状态的,查询后会publish然后再修改状态
	 */
	public static final int NEW=0;
	public static final int READY=1;
	public static final int CANCEL=2;
	public static final int SEND_SUCCESS=3;
	public static final int SEND_FAIL=4;
	public static final int RECEIVER=5;
	public static final int FINISHED=6;
	
	
	private AppEvent event;
	
	//0 new 1 ready 2 cancel
	private int status;
}
