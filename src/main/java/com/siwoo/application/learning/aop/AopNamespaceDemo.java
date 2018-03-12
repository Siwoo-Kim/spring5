package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.common.Documentarist;
import com.siwoo.application.learning.common.ProxyBeanExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopNamespaceDemo {

    public static void main(String[] args) {
        ApplicationContext c = new ClassPathXmlApplicationContext("spring/aop/aop-context.xml");

        ProxyBeanExecutor executor = c.getBean(ProxyBeanExecutor.class);
        int num = executor.execute();
        System.out.println(num);
    }
}
