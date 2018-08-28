/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午3:26:17
* 
*/
package com.tmall.server.product.server.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* 
* @author joker 
* @date 创建时间：2018年8月21日 下午3:26:17
*/
@Target(ElementType.METHOD)
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public @interface URLAuthCheck
{
	
}
