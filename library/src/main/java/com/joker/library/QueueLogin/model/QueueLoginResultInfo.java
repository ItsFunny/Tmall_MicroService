/**
*
* @author joker 
* @date 创建时间：2018年2月3日 下午2:22:21
* 
*/
package com.joker.library.QueueLogin.model;

import com.joker.library.QueueLogin.service.QueueLoginService;

/**
* 用来显示排队登陆的状态信息
* @author joker 
* @date 创建时间：2018年2月3日 下午2:22:21
*/
public class QueueLoginResultInfo
{
	/**
	* 状态码
	* @author joker 
	* @date 创建时间：2018年2月3日 下午2:26:00
	*/
	private Integer code;
	/**
	* 排队的信息
	* @author joker 
	* @date 创建时间：2018年2月3日 下午2:26:02
	*/
	private String msg;
	/**
	* 若处于排队状态,则该表示排在第几个位置
	* @author joker 
	* @date 创建时间：2018年2月3日 下午2:26:05
	*/
	private Integer index;
	
	public static QueueLoginResultInfo sucess()
	{
		QueueLoginResultInfo queueLoginResultInfo=new QueueLoginResultInfo();
		queueLoginResultInfo.setCode(QueueLoginService.SUCESS);
		queueLoginResultInfo.setMsg("sucess");
		return queueLoginResultInfo;
	}
	public static QueueLoginResultInfo inLine(Integer index)
	{
		QueueLoginResultInfo queueLoginResultInfo=new QueueLoginResultInfo();
		queueLoginResultInfo.setCode(QueueLoginService.WAITLOGIN);
		queueLoginResultInfo.setMsg("wait login");
		queueLoginResultInfo.setIndex(index);
		return queueLoginResultInfo;
	}
	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code = code;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public Integer getIndex()
	{
		return index;
	}
	public void setIndex(Integer index)
	{
		this.index = index;
	}
	
	
}
