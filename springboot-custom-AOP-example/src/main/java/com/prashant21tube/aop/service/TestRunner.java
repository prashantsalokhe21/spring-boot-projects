package com.prashant21tube.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {
	
	@Autowired
	CustomService customService;

	@Override
	public void run(String... args) throws Exception {
		customService.sampleMethod("view","ABC",1234);
		
	}

}
