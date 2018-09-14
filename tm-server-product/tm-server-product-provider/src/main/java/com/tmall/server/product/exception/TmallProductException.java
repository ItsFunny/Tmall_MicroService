/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午5:47:24
* 
*/
package com.tmall.server.product.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午5:47:24
*/
public class TmallProductException extends TmallBussinessException
{

	public TmallProductException(String message, Throwable cause)
	{
		super(message, cause);
	}

	
	public TmallProductException(ErrorCodeEnum enum1)
	{
		super(enum1);
	}
	public TmallProductException(ErrorCodeEnum enum1,String msg)
	{
		super(enum1,msg);
	}
	public TmallProductException(ErrorCodeEnum enum1,Throwable cause)
	{
		super(enum1.getMsg(),cause);
	}

	

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月14日 下午5:47:44
	*/
	private static final long serialVersionUID = -8628550619450909041L;

}
