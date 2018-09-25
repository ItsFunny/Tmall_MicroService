/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月21日 下午4:30:46
* 
*/
package com.joker.library.sqlextention;

import lombok.Data;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月21日 下午4:30:46
*/
@Data
public abstract class AbstractSQLExtentionModel
{
	private String tableName;
	
	public abstract Number getUniquekey();
	

}
