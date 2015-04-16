package com.rabbit.spring.aop.custom;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

	@Override
	public boolean matches(Method arg0, Class<?> arg1, Object[] arg2) {
		int x = (Integer) arg2[0];
	    return (x != 100);
	}

	 public ClassFilter getClassFilter() {
	    return new ClassFilter() {
	      public boolean matches(Class<?> cls) {
	        return (cls == MyClass.class);
	      }
	    };
	 }
}
