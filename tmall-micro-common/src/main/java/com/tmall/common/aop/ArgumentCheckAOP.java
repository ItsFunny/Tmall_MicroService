/**
*
* @author joker 
* @date 创建时间：2018年9月13日 上午10:04:00
* 
*/
package com.tmall.common.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.dto.ResultDTO;
import com.tmall.common.annotation.ArgumentCheckAnnotation;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
 * 用于校验参数
 * 
 * @author joker
 * @date 创建时间：2018年9月13日 上午10:04:00
 */

@Aspect
@Component
public class ArgumentCheckAOP
{
	@Pointcut("@annotation(com.tmall.common.annotation.ArgumentCheckAnnotation)")
	public void checkArgument()
	{
	}

	@Before("checkArgument() && @annotation(annotation)")
	public void checkArgu(JoinPoint joinPoint, ArgumentCheckAnnotation annotation)
	{
		int checkParamIndex = annotation.checkParamIndex();
		Object[] args = joinPoint.getArgs();
		if (!(args[checkParamIndex] instanceof UserRequestDTO))
		{
			throw new TmallBussinessException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.TYPE_ILLEGAL_ARGUMENT,
					"类型不匹配,需要的类型为:" + UserRequestDTO.class + "传递过来的类型为:" + args[checkParamIndex].getClass()));
		}
		UserRequestDTO userRequestDTO = (UserRequestDTO) args[checkParamIndex];
		UserDTO user = userRequestDTO.getUser();
		if (null == user)
		{
			throw new TmallBussinessException(ErrorCodeEnum.parseEnum(ErrorCodeEnum.MISSING_ARGUMENT, "user不能为空"));
		}
		Map<String, Object> extProps = userRequestDTO.getExtProps();
		String mapKey = annotation.mapKey();
		Class<?> type = annotation.parseType();
		Object object = extProps.get(mapKey);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			mapper.convertValue(object, type);
		} catch (Exception e)
		{
			throw new TmallBussinessException(ErrorCodeEnum.appendMsg(ErrorCodeEnum.ARGUMENT_PARSE_ERROR,
					"参数:" + object + "无法转换为:" + type + "类型"));
		}

	}

}
