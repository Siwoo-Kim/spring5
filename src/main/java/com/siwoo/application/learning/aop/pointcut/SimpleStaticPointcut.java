package com.siwoo.application.learning.aop.pointcut;

import com.siwoo.application.learning.common.GreatGuitarist;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "sing".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> GreatGuitarist.class == clazz;
    }
}
