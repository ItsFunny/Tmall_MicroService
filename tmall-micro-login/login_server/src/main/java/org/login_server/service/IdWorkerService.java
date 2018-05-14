/**
*
* @author joker 
* @date 创建时间：2018年5月14日 下午12:53:53
* 
*/
package org.login_server.service;

import java.util.Collection;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 下午12:53:53
 */
public interface IdWorkerService
{
	long nextId();

	Collection<Long> nextIds(int num);
}
