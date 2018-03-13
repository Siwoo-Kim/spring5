package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.common.Documentarist;
import com.siwoo.application.learning.common.ProxyBeanExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class AopNamespaceDemo {

    public static void main(String[] args) {
        ApplicationContext c = new ClassPathXmlApplicationContext("spring/aop/aop-context.xml");
        ProxyBeanExecutor executor = c.getBean("proxyExecutor",ProxyBeanExecutor.class);
        int num = executor.execute();
        System.out.println(num);

        executor = c.getBean("scannedProxyBeanExecutor",ProxyBeanExecutor.class);
        Assert.notNull(executor.getProxyTarget(),"Must not be null");
        num = executor.execute();
        System.out.println(num);
    }
}
