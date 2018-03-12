package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.aop.pointcut.SimpleStaticPointcut;
import com.siwoo.application.learning.common.GoodGuitarist;
import com.siwoo.application.learning.common.GreatGuitarist;
import com.siwoo.application.learning.common.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class StaticPointcutDemo {

    public static void main(String[] args) {
        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Singer goodGuitarist = getProxy(new GoodGuitarist(),advice,pointcut);
        Singer greatGuitarist = getProxy(new GreatGuitarist(),advice,pointcut);

        goodGuitarist.sing();
        greatGuitarist.sing();
    }

}
