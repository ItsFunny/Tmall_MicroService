/**
*
* @author joker 
* @date 创建时间：2018年8月20日 上午11:43:43
* 
*/
package com.tmall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * bena转换之间出错
 * 
 * @author joker
 * @date 创建时间：2018年8月20日 上午11:43:43
 */
@ResponseStatus(value = HttpStatus.SEE_OTHER)
public class TmallParseBeanException extends TmallBussinessException
{

	public TmallParseBeanException(int code, String message)
	{
		super(code, message);
		// TODO Auto-generated constructor stub
	}
	
	public TmallParseBeanException(Object original, Object destination,int code, String message)
	{
		super(code, message);
		this.original=original;
		this.destination=destination;
	}

	private Object original;
	private Object destination;



	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年8月20日 上午11:44:16
	 */
	private static final long serialVersionUID = 4382933806922169408L;

	public Object getOriginal()
	{
		return original;
	}

	public void setOriginal(Object original)
	{
		this.original = original;
	}

	public Object getDestination()
	{
		return destination;
	}

	public void setDestination(Object destination)
	{
		this.destination = destination;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
