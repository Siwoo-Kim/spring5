package com.siwoo.application.learning.aop.pointcut;

import com.siwoo.application.learning.common.SampleBean;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut{

    //Class filtering
    public ClassFilter getClassFilter() {
        return clazz -> clazz == SampleBean.class;
    }

    //Static filtering
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("static checking invoke");
        //return "foo".equals(name) || "bar".equals(name);
        //static checking is once at the runtime
        String name = method.getName();
        return "foo".equals(name);
    }

    //Dynamic filtering
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("dynamic checking invoke");
        if(args.length != 1){return false;}
        int value = (int) args[0];
        return value == 100;
    }
}
