package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleTargetAroundAdivce;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.Assert;

public class SimpleTargetDemo {

    public static void main(String[] args) {
        SimpleTarget target = new SimpleTarget();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleTargetAroundAdivce());
        proxyFactory.setTarget(target);

        SimpleTarget proxy = (SimpleTarget) proxyFactory.getProxy();
        Assert.notNull(proxy,"proxy must not be null");
        proxy.greeting();
        target.greeting();
    }
}
