/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午9:31:19
* 
*/
package com.tmall.common.service;

import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年9月3日 上午9:31:19
*/
public interface PageBaseService<T>
{
	PageResponseDTO<T> findByPage(PageRequestDTO pageRequestDTO);
}
