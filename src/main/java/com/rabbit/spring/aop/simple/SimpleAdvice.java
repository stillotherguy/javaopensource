package com.rabbit.spring.aop.simple;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleAdvice {

	public static void main(String[] args) {
		TargetInterface bean = new TargetBean();
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(bean);
		pf.addAdvice(new SimpleAfterReturningAdvice());
		
		TargetInterface proxy = (TargetInterface) pf.getProxy();
		proxy.foo();
	}
	
	
	private static class SimpleAfterReturningAdvice implements AfterReturningAdvice {

		@Override
		public void afterReturning(Object returnValue, Method method, Object[] args,
				Object target) throws Throwable {
			System.out.printf("method %s returned value %s\n", method.getName(), target);
		}
		
	}
}
