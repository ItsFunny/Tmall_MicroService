/**
*
* @author joker 
* @date 创建时间：2018年6月24日 上午11:36:04
* 
*/
package com.tmall.server.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 上午11:36:04
 */
@Component
public class BaseService
{
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	protected RestTemplate restTemplate;

	protected String getServerUrl(String serverName)
	{
		ServiceInstance serviceInstance = loadBalancerClient.choose(serverName);
		if (null != serviceInstance)
		{
			String host = serviceInstance.getHost();
			int port = serviceInstance.getPort();
			return String.format("http://%s:%s", host, port);
		} else
		{
			throw new RuntimeException("server is not exists");
		}
	}
}
