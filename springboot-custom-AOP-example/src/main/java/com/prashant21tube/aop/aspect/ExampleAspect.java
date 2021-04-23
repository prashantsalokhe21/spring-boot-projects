package com.prashant21tube.aop.aspect;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		
		// Method Information
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		   // Method args
        System.out.println("Method args names:");
        Arrays.stream(signature.getParameterNames())
          .forEach(s -> System.out.println("arg name: " + s));

        System.out.println("Method args types:");
        Arrays.stream(signature.getParameterTypes())
          .forEach(s -> System.out.println("arg type: " + s));

        System.out.println("Method args values:");
        Arrays.stream(joinPoint.getArgs())
          .forEach(o -> System.out.println("arg value: " + o.toString()));
		
		Object proceed = joinPoint.proceed();
		
		long executionTime = System.currentTimeMillis() - start;
		
		System.out.println(joinPoint.getSignature() + "executed in" + executionTime + "ms");
		
		
		return proceed;
	}

}
