/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:34:38
* 
*/
package com.joker.library.utils;

import com.joker.library.dto.ResultDTO;
import com.joker.library.enums.RestAPIStatus;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 下午1:34:38
 */
public class ResultUtils<T>
{
	public static<T> ResultDTO<T> sucess(T data, String msg)
	{
		ResultDTO<T> ResultDTO = new ResultDTO<>();
		ResultDTO.setCode(RestAPIStatus.SUCESS.ordinal());
		ResultDTO.setData(data);
		ResultDTO.setMsg(msg);
		return ResultDTO;
	}
	
	public static<T> ResultDTO<T>sucess()
	{
		return sucess(null, "sucess");
	}
	public static<T> ResultDTO<T> sucess(T data)
	{
		return sucess(data, "sucess");
	}

	public static<T> ResultDTO<T> fail(T data, String msg)
	{
		ResultDTO<T> ResultDTO = new ResultDTO<>();
		ResultDTO.setData(data);
		ResultDTO.setCode(RestAPIStatus.FAIL.ordinal());
		ResultDTO.setMsg(msg);
		return ResultDTO;
	}

	public static<T> ResultDTO<T> fail(String msg)
	{
		return fail(null, msg);
	}

	public static<T> ResultDTO<T> fail()
	{
		return fail("fail");
	}

}
