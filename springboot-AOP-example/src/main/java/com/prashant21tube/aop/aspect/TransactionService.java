package com.prashant21tube.aop.aspect;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionService {
	
	@Pointcut("execution(public String com.prashant21tube.aop.dao.EmployeeDAO.saveEmployee(..))")
	public void p1() {
		
	}
	
	@Before("p1()")
	public void beginTransaction(JoinPoint joinPoint) {
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		methodSignature.getParameterNames();
		for(Object codeSignature : joinPoint.getArgs()) {
			System.out.println(codeSignature);
		}
		System.out.println("Start TX..");
	}
	
	
	@After("p1()")
	public void fileSend() {
		System.out.println("File send");
	}
	
	@AfterReturning(value="p1()", returning="obj")
	public void commitTransaction(Object obj) {
		System.out.println("Commit Tx.."+ obj);
	}
	
	@AfterThrowing(value="p1()", throwing= "th")
	public void rollbackTransaction(Throwable th) {
		System.out.println("Rollback Tx.."+ th.getMessage());
	}
	
	
	

}
