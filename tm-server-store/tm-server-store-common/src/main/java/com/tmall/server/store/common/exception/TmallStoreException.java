/**
*
* @author joker 
* @date 创建时间：2018年9月1日 上午10:49:13
* 
*/
package com.tmall.server.store.common.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月1日 上午10:49:13
*/
public class TmallStoreException extends TmallBussinessException
{

	public TmallStoreException(ErrorCodeEnum enum1)
	{
		super(enum1);
		// TODO Auto-generated constructor stub
	}
	public TmallStoreException(ErrorCodeEnum enum1,Throwable cause)
	{
		super(enum1,cause);
	}
	public TmallStoreException(ErrorCodeEnum enum1,String msg)
	{
		super(enum1,msg);
	}
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月1日 上午10:49:26
	*/
	private static final long serialVersionUID = 3340787055190905716L;

}
