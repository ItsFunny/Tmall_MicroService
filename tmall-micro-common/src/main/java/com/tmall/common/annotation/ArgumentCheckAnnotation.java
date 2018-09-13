/**
*
* @author joker 
* @date 创建时间：2018年9月13日 上午9:57:30
* 
*/
package com.tmall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月13日 上午9:57:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ArgumentCheckAnnotation
{
	Class<?> parseType();

	String mapKey();
	
	int checkParamIndex() default 0;

}
