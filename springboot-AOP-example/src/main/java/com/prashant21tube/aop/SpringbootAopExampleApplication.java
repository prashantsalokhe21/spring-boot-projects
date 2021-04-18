package com.prashant21tube.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringbootAopExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAopExampleApplication.class, args);
	}

}
