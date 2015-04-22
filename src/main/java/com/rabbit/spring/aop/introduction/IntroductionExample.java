package com.rabbit.spring.aop.introduction;

import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

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
		
		
		//另一种方法
		DelegatingIntroductionInterceptor in = new DelegatingIntroductionInterceptor(new IsModified() {

			@Override
			public boolean isModified() {
				return true;
			}
			
		});
		
		Advisor advisor1 = new DefaultIntroductionAdvisor(in);
		
		ProxyFactory pf1 = new ProxyFactory();
		pf1.setTarget(target);
		pf1.addAdvisor(advisor1);
		
		TargetBean proxy1 = (TargetBean) pf1.getProxy();
		
		System.out.println("is target bean " + (proxy1 instanceof TargetBean));
		System.out.println("is ismodified " + (proxy1 instanceof IsModified));
	}
}
