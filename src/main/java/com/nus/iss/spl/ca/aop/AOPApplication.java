package com.nus.iss.spl.ca.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AOPApplication {

	public static void main(String[] args) {
		SpringApplication.run(AOPApplication.class, args);
	}
}
