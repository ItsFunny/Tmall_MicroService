/**
*
* @author joker 
* @date 创建时间：2018年5月10日 上午11:19:29
* 
*/
package org.login_server.controller;

import static org.assertj.core.api.Assertions.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.login_server.config.KeyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tmall.common.model.User;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月10日 上午11:19:29
 */
@Controller
public class TestController
{
	@Autowired
	private KeyProperties keyProperties;
	@RequestMapping(value = "/cp1")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("cp1");
		modelAndView.addObject("intVar", 100);
		modelAndView.addObject("date",new Date());
		modelAndView.addObject("nullVar",null);
		
		User user=new User();
		user.setUsername("joker");
		user.setPassword("123");
		modelAndView.addObject("user",user);
		
		
		List<String>strings=new ArrayList<>();
		strings.add("joker");
		strings.add("clown");
		strings.add("cry");
		modelAndView.addObject("myList",strings);
		
		
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("java", "hello");
		map.put("python", "hello");
		map.put("c++", "hello");
		modelAndView.addObject("map",map);
		
		return modelAndView;
	}
	@RequestMapping("/rsa/test")
	@ResponseBody
	public String test()
	{
		return keyProperties.getLoginPublicKey().toString();
	}

}
