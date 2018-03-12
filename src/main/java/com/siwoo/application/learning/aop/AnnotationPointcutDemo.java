package com.siwoo.application.learning.aop;

import com.siwoo.application.learning.aop.advice.SimpleAdvice;
import com.siwoo.application.learning.aop.pointcut.Adviced;
import com.siwoo.application.learning.common.GrammyGuitarist;
import com.siwoo.application.learning.common.Guitar;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import static com.siwoo.application.learning.aop.util.ProxyUtil.getProxy;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        //AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Adviced.class);
        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(Adviced.class);
        GrammyGuitarist singer = getProxy(new GrammyGuitarist(),new SimpleAdvice(),pointcut);

        singer.sing();
        singer.sing(new Guitar());
        singer.sing2();
        singer.rest();
        ((GrammyGuitarist)singer).talk();
    }
}
