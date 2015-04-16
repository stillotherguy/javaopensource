package com.rabbit.spring.aop.composable;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

public class Main {
	public static void main(String[] args) {
		TargetBean targetBean = new TargetBean();
		
		ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new Foo1MethodMatcher());
		
		testInvoke(getProxy(pointcut, targetBean));
		
		pointcut.union(new Bar1MethodMatcher());
		testInvoke(getProxy(pointcut, targetBean));
		
		pointcut.union(new Bar2MethodMatcher());
		testInvoke(getProxy(pointcut, targetBean));
		
    }
	
	private static TargetBean getProxy(ComposablePointcut pc, TargetBean target) {
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        return (TargetBean) pf.getProxy();
    }

    private static void testInvoke(TargetBean proxy) {
        proxy.foo1();
        proxy.bar1();
        proxy.bar2();
    }

    private static class Foo1MethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> clazz) {
			if(method.getName().equals("foo1")) {
				return true;
			}
			
			return false;
		}
    	
    }
    
    private static class Bar1MethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> clazz) {
			if(method.getName().equals("bar1")) {
				return true;
			}
			
			return false;
		}
    	
    }
    
    private static class Bar2MethodMatcher extends StaticMethodMatcher {

		@Override
		public boolean matches(Method method, Class<?> clazz) {
			if(method.getName().equals("bar2")) {
				return true;
			}
			
			return false;
		}
    	
    }
}
