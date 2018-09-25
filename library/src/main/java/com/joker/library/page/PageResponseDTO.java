/**
*
* @author joker 
* @date 创建时间：2018年8月27日 下午12:57:28
* 
*/
package com.joker.library.page;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月27日 下午12:57:28
 */
public class PageResponseDTO<T>
{

	private T data;
	private Integer pageSize;
	private Integer pageNum;
	private Long totalCount;
	private Integer maxPage;

	public PageResponseDTO()
	{
		super();
	}
	public PageResponseDTO(T data, Integer pageSize, Integer pageNum, Long totalCount)
	{
		this.data = data;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalCount = totalCount;
		this.maxPage = (int) ((totalCount % pageSize) == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1);
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Long getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(Long totalCount)
	{
		this.totalCount = totalCount;
	}

	public Integer getMaxPage()
	{
		return maxPage;
	}

	public void setMaxPage(Integer maxPage)
	{
		this.maxPage = maxPage;
	}

}
