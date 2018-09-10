/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午4:08:56
* 
*/
package com.tmall.batch.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月8日 下午4:08:56
*/
public class TmallBatchException extends TmallBussinessException
{

	public TmallBatchException(ErrorCodeEnum enum1)
	{
		super(enum1);
		// TODO Auto-generated constructor stub
	}
	public TmallBatchException(ErrorCodeEnum enum1,Throwable cause)
	{
		super(enum1,cause);
	}
	

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月8日 下午4:09:09
	*/
	private static final long serialVersionUID = 1195770074701303555L;

}
