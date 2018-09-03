/**
*
* @author joker 
* @date 创建时间：2018年9月2日 下午5:12:03
* 
*/
package com.tmall.common.service;

import java.util.Map;

import com.joker.library.model.PageRequestDTO;
import com.joker.library.model.PageResponseDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月2日 下午5:12:03
 */
public interface BaseService<T>
{
	PageResponseDTO<T> findByPage(PageRequestDTO pageRequestDTO);

	
}
