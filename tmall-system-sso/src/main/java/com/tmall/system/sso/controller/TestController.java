/**
*
* @author joker 
* @date 创建时间：2018年6月12日 下午4:15:20
* 
*/
package com.tmall.system.sso.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tmall.common.dto.ResultDTO;
import com.tmall.server.spi.auth.IAuthFeignService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月12日 下午4:15:20
 */
@RestController
public class TestController
{
	@Autowired
	private IAuthFeignService authFeignService;

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

//		ServiceInstance instance = loadBalancerClient.choose("auth");
//		String host = instance.getHost();
//		int port = instance.getPort();
//		String url="http://"+host+":"+port+"/test";
////		"http://localhost:8003/test"
//		ResultDTO<String> resultDTO=restTemplate.getForObject(url, ResultDTO.class);
		ResultDTO<String> resultDTO = authFeignService.test();
		return resultDTO.getMsg();
	}
}
