/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
* 
*/
package com.tmall.common.enums;

/**
* server-auth服务1开头
* server-user服务2开头
* server-store服务3开头
* server-user服务4开头
* MQ 		5开头
* common 0开头
* system-admin系统 SYS开头 1开头
* system-batch系统 SYS开头 2开头
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
*/
public enum ErrorCodeEnum
{

	INERTNATL_SERVER_ERROR(0,"服务内部故障:%s"),
	UNKNOWN_EXCEPTION(1,"服务故障,未知原因"),
	MISSING_ARGUMENT(2,"缺少参数:%s"),
	TRANSCATION_ROLLBACK_ON_PURPOSE(3,"原因:%s,手动抛异常,回滚事务"),
	FIND_MULTIPLE_RECORDS(4,"找到多条记录,本应该只有1条记录"),
	INTERNAL_SERVICE_CALL_ERROR(5,"服务间调用出错"),
	INTERNAL_SERVICE_CALL_RETURN_FAIL(6,"服务间调用,服务返回失败"),
	INTERNAL_SERVICE_ERROR(7,"服务自身业务出错"),
	/*
	 * auth-server
	 */
	AUTH_MULTIROLES_1001(1001,"同个店铺匹配到多个角色信息"),
	
	/*
	 * user-server
	 */
	USER_NOT_EXIST_2001(2001,"用户不存在"),
	USER_TOKEN_INVALID(2002,"无效的用户token信息"),
	USER_TOKEN_EXPIRED(2003,"用户token信息过期,重新登录"),
	USER_NOT_LOGIN(2004,"用户尚未登录"),
	/*
	 * store-server
	 */
	STORE_INTERNAL_SERVER_3001(3001,"服务调用,内服服务出错"),
	STORE_NOT_EXIST_3002(3002,"店铺信息不存在"),
	STORE_MISSING_ARGUMENT(3003,"缺少参数:%s"),
	STORE_WRONG_CLASS_TYPE(3004,"错误的参数类型,需要的参数类型为:%s,传递的参数类型为:%s"),
	
	STORE_LOCALMESSAGE_ILLEGAL_DATA(3005,"本地消息表中的:%s,记录不合法,记录为:%s"),
	
	@Deprecated
	STORE_MQ_NOT_SUPPORT_TYPE(3003,"store发送消息的对象类型不是正确的类型"),
	@Deprecated
	STORE_MQ_MISSING_ARGUMETN(3004,"store 服务发送消息的时候缺少参数:%s"),
	
	
	/*
	 * MQ
	 */
	MQ_ILLEGAL_CORRELATIONDATA(5000,"消息体中的CorrelationData类型不正确,正确的类型应该是TmallCorrealtionData"),
	MQ_CALLBACK_ARGUMENT_CANT_BE_NULL(5001,"消息队列回调函数中callBackData不能为空"),
	MQ_MESSAGE_CANT_FIND_MATCH_RECORD_FORM_DB(5002,"无法在数据库中找到对应的消息"),
	MQ_ADD_JOB_TO_QUEUE_FAIL(5003,"往任务消息队列中添加任务失败"),
	MQ_MESSAGE_MISSING(5004,"消息丢失了"),
	MQ_MESSAGE_MISSING_SERVER_NAME(5005,"%s服务不存在"),
	
	
	
	SYS_ADMIN_SERVER_10001(10001,"服务内部错误"),
	
	
	
	/*
	 * batch
	 */
	SYS_BATCH_CONSUME_MESSAGE_ERROR(20001,"消费信息的时候出错"),
	;
	
	public static ErrorCodeEnum parseEnum(ErrorCodeEnum enum1,Object ...args)
	{
		String msg = enum1.getMsg();
		String newMsg = String.format(msg, args);
		enum1.setMsg(newMsg);
		return enum1;
	}
	public static ErrorCodeEnum getEnum(int code)
	{
		ErrorCodeEnum[] enums = ErrorCodeEnum.values();
		for (ErrorCodeEnum errorCodeEnum : enums)
		{
			if(errorCodeEnum.getCode().equals(Integer.valueOf(code)))
			{
				return errorCodeEnum;
			}
		}
		return ErrorCodeEnum.UNKNOWN_EXCEPTION;
	}
	
	
	private Integer code;
	
	private String msg;

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

	private ErrorCodeEnum(Integer code, String msg)
	{
		this.code = code;
		this.msg=code+":"+msg;
	}
	
	
}
