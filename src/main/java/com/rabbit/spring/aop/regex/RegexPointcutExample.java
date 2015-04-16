package com.rabbit.spring.aop.regex;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegexPointcutExample {
	public static void main(String[] args) {
		TargetBean targetBean = new TargetBean();
		
		JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
		pc.setPattern(".*foo.*");
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(targetBean);
		pf.addAdvisor(advisor);
		
		TargetBean proxy = (TargetBean) pf.getProxy();
		
		proxy.foo1();
		proxy.bar1();
		proxy.bar2();
	}
}
