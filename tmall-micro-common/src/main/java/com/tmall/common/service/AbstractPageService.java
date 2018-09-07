/**
*
* @author joker 
* @date 创建时间：2018年9月3日 上午9:34:14
* 
*/
package com.tmall.common.service;

import java.util.Map;

import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.utils.PageResultUtil;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月3日 上午9:34:14
 */
public abstract class AbstractPageService<T> implements PageBaseService<T>
{
	@Override
	public PageResponseDTO<T> findByPage(PageRequestDTO pageRequestDTO)
	{
		Long count = countByCondition(pageRequestDTO.getData());
		if (count <= 0)
		{
			return PageResultUtil.emptyPage();
		} else
		{
			Integer pageNum = pageRequestDTO.getPageNum();
			Integer pageSize = pageRequestDTO.getPageSize();
			if (pageNum <= 0)
			{
				pageNum = 1;
			}
			if (pageSize <= 0)
			{
				pageSize = 10;
			}
			Map<String, Object> data = pageRequestDTO.getData();
			T datas = null;
			if (data == null || data.isEmpty())
			{
				datas = findAllByPage((pageNum - 1) * pageSize, pageSize);
			} else
			{
				datas = findByCondition((pageNum - 1) * pageSize, pageSize, pageRequestDTO.getData());
			}
			PageResponseDTO<T> pageResponseDTO = new PageResponseDTO<T>(datas, pageSize, pageNum, count);
			return pageResponseDTO;
		}
	}

	public abstract T findByCondition(Integer start, Integer end, Map<String, Object> condition);

	public abstract T findAllByPage(Integer start, Integer end);

	public abstract Long countByCondition(Map<String, Object> condition);

}
