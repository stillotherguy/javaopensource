package com.rabbit.spring.aop.name;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class Main {
	public static void main(String[] args) {
		TargetBean targetBean = new TargetBean();
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.addMethodName("bar1");
		pointcut.addMethodName("bar2");
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(targetBean);
		pf.addAdvisor(advisor);
		
		targetBean.foo1();
		targetBean.bar1();
		targetBean.bar2();
	}
}
