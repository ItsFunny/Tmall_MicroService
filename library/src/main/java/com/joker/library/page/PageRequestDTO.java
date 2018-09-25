/**
*
* @author joker 
* @date 创建时间：2018年8月27日 上午11:02:37
* 
*/
package com.joker.library.page;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月27日 上午11:02:37
 */
@Data
public class PageRequestDTO
{
	private Integer pageSize;

	private Integer pageNum;
	
	private String tablePrefixName;
	
	private Map<String, Object>data;
	
	//查询一条记录时候的关键字,如在user表中这个就是userId
	private Object singleKey;
	
	private boolean isSingal;
	
	public void setSingle()
	{
		this.isSingal=true;
	}
	
	public PageRequestDTO()
	{
		this.data=new HashMap<>();
	}

}
