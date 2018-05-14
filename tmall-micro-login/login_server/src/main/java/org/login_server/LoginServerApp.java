package org.login_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class LoginServerApp
{
	public static void main(String[] args)
	{
		SpringApplication.run(LoginServerApp.class, args);
	}
}
