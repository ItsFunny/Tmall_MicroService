/**
*
* @author joker 
* @date 创建时间：2018年9月6日 下午1:44:44
* 
*/
package com.tmall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月6日 下午1:44:44
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ArgumentCheck
{
	String  type();
	
	
	
}
 