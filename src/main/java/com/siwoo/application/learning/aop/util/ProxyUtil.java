package com.siwoo.application.learning.aop.util;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyUtil {

    public static <T> T getProxy(ProxyFactory px, T target, Advice adivce) {
        px.setTarget(target);
        px.addAdvice(adivce);
        return (T) px.getProxy();
    }
}
