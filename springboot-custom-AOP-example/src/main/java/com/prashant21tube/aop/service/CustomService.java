package com.prashant21tube.aop.service;

import org.springframework.stereotype.Component;

import com.prashant21tube.aop.aspect.LogExecutionTime;

@Component
public class CustomService {

	@LogExecutionTime
	public void sampleMethod() throws InterruptedException {
		Thread.sleep(2000);
	}
}
