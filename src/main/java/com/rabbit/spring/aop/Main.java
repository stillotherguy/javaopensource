package com.rabbit.spring.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {
	public static void main(String[] args) {
		MyClass myclass = new MyClass();
		Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(myclass);
		pf.addAdvisor(advisor);
		MyClass proxy = (MyClass) pf.getProxy();
		proxy.foo(100);
		proxy.bar();
	}
}
