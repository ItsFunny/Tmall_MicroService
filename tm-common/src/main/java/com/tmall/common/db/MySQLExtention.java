/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午9:10:34
* 
*/
package com.tmall.common.db;

/**
* 
* 当数据库进行扩展之后,这里可以进行相应的更改,从而不需要深入到服务中
* 更改代码,如默认的user 水平分片分了2个tmall_user0,tmall_user1 采用的方法是userId&1
* 但是如果数据库再次水平分片,userId&1 就不行了
* @author joker 
* @date 创建时间：2018年6月4日 上午9:10:34
*/
public interface MySQLExtention
{
	/**
	 * @param info	包含了分片的主键和 总数
	 * @return
	 * @author joker 
	 * @date 创建时间：2018年6月4日 上午9:27:26
	 */
	ExtentionResult getTableNum(String tableName,ExtentionInfo info);
	
	ExtentionResult getTableNum(String tableName,Long id);
}
