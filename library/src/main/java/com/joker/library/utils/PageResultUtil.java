/**
*
* @Description
* @author joker 
* @date 创建时间：2018年6月30日 下午12:51:11
* 
*/
package com.joker.library.utils;

import java.util.Arrays;
import java.util.Collections;

import com.joker.library.page.PageResponseDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年6月30日 下午12:51:11
*/
public class PageResultUtil
{
	public static <T> PageResponseDTO<T> emptyPage()
	{
		PageResponseDTO<T> pageDTO=new PageResponseDTO<>();
		pageDTO.setTotalCount(0L);
		pageDTO.setMaxPage(0);
		pageDTO.setPageNum(0);
		pageDTO.setData((T) Collections.emptyList());
		return pageDTO;
	}
	public static<T>PageResponseDTO<T> singleRecordPage(T data)
	{
		PageResponseDTO<T>pageResponseDTO=new PageResponseDTO<>();
		pageResponseDTO.setTotalCount(1L);
		pageResponseDTO.setMaxPage(1);
		pageResponseDTO.setPageNum(1);
		pageResponseDTO.setPageSize(10);
		pageResponseDTO.setData(data);
		return pageResponseDTO;
	}
}
