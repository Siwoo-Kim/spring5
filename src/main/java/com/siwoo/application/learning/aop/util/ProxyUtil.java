package com.siwoo.application.learning.aop.util;

import com.siwoo.application.learning.common.GoodGuitarist;
import com.siwoo.application.learning.common.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyUtil {

    public static <T> T getProxy(ProxyFactory px, T target, Advice adivce) {
        px.setTarget(target);
        px.addAdvice(adivce);
        return (T) px.getProxy();
    }


    public static <T extends Object> T getProxy(T target, Advice advice, Pointcut pointcut) {
        ProxyFactory px = new ProxyFactory();
        Advisor av = new DefaultPointcutAdvisor(pointcut,advice);
        px.setProxyTargetClass(true);
        px.addAdvisor(av);
        px.setTarget(target);
        return (T) px.getProxy();
    }
}
