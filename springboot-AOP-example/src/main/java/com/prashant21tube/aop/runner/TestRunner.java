package com.prashant21tube.aop.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.prashant21tube.aop.dao.EmployeeDAO;

@Component
public class TestRunner implements CommandLineRunner {
	
	@Autowired
	EmployeeDAO employeeDAO;
	

	@Override
	public void run(String... args) throws Exception {
		employeeDAO.saveEmployee(12, "Prashant");
		
	}

}
