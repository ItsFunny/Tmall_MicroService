/**
*
* @author joker 
* @date 创建时间：2018年6月22日 上午9:58:29
* 
*/
package com.tmall.common.exception;

/**
* 
* @author joker 
* @date 创建时间：2018年6月22日 上午9:58:29
*/
public class TmallUserNotLoginException extends RuntimeException
{

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年6月22日 上午9:58:51
	*/
	private static final long serialVersionUID = -3303750026848021676L;
	private String msg;
	public TmallUserNotLoginException(String msg)
	{
		super(msg);
		this.msg=msg;
	}
	public TmallUserNotLoginException()
	{
		
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
}
