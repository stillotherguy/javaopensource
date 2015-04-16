package com.rabbit.spring.aop.controlflow;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
        main.run();
    }

    public void run() {
        TargetBean target = new TargetBean();

        Pointcut pc = new ControlFlowPointcut(Main.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pc,
                new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        TargetBean proxy = (TargetBean) pf.getProxy();

        System.out.println("Trying normal invoke");
        proxy.foo();
        System.out.println("Trying under ControlFlowExample.test()");
        test(proxy);
    }

    private void test(TargetBean bean) {
        bean.foo();
    }
}
