package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.aop.pointcut.SimpleDynamicPointcut;
import com.siwoo.application.learning.common.SampleBean;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        SimpleDynamicPointcut pointcut = new SimpleDynamicPointcut();
        SimpleAdvice advice = new SimpleAdvice();
        SampleBean sampleBean = getProxy(new SampleBean(),advice,pointcut);
        sampleBean.bar();
        sampleBean.foo(1);
        sampleBean.foo(100);
    }
}
