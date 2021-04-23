package com.prashant21tube.aop.dao;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO {
	
	
	public String saveEmployee(int i, String str) {
		System.out.println("Employee saved!!");
		/*if(new Random().nextInt(15) <= 10) {	
			throw new RuntimeException("Dummy Exception!!");
		}*/
		return "Hello";
	}

}
