package com.rabbit.spring.aop.introduction;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionExample {
	public static void main(String[] args) {
		TargetBean target = new TargetBean();
		target.setName("Ethan");
		
		IntroductionAdvisor advisor = new IsModifiedAdvisor();
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		pf.setOptimize(true);
		
		TargetBean proxy = (TargetBean) pf.getProxy();
		IsModified proxyInterface = (IsModified) proxy;
		
		System.out.println("is target bean " + (proxy instanceof TargetBean));
		System.out.println("is ismodified " + (proxy instanceof IsModified));
		System.out.println("changed? " + proxyInterface.isModified());
		
		proxy.setName("Zhang");
		System.out.println("changed? " + proxyInterface.isModified());
		proxy.setName("Li");
		System.out.println("changed? " + proxyInterface.isModified());
	}
}
