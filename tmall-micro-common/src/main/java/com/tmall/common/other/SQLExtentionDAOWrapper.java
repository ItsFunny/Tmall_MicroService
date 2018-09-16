/**
*
* @author joker 
* @date 创建时间：2018年9月15日 下午2:32:38
* 
*/
package com.tmall.common.other;

import lombok.Data;

/**
* 
* @author joker 
* @date 创建时间：2018年9月15日 下午2:32:38
*/
@Data
public class SQLExtentionDAOWrapper<T>
{
	private ISQLExtentionCRUDDao<T> baseDao;
	private String tableDetailName;
}
