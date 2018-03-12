package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.common.GrammyGuitarist;
import com.siwoo.application.learning.common.Guitar;
import com.siwoo.application.learning.common.Singer;
import org.springframework.aop.support.NameMatchMethodPointcut;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class NamePointcutDemo {
    public static void main(String[] args) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");
        SimpleAdvice advice = new SimpleAdvice();
        Singer grammySinger = getProxy(new GrammyGuitarist(),advice,pointcut);

        grammySinger.sing();
        grammySinger.sing(new Guitar());
        grammySinger.rest();
        ((GrammyGuitarist)grammySinger).talk();
    }
}
