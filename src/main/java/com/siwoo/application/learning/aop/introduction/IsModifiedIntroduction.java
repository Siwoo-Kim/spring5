package com.siwoo.application.learning.aop.introduction;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.util.ObjectUtils.*;

public class IsModifiedIntroduction extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModdified = false;
    private Map<Method,Method> cache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModdified;
    }

    @Override
    public Object invoke(MethodInvocation inv) throws Throwable {

        if(!isModdified){
            Method method = inv.getMethod();
            Object[] args = inv.getArguments();
            Object target = inv.getThis();
            if(method.getName().startsWith("set") && args.length == 1){
                Method getter = getGetter(method);
                Object setValue = args[0];
                Object oldValue = getter.invoke(target,null);

                isModdified = check(setValue,oldValue);
            }
        }

        return super.invoke(inv);
    }


    private boolean check(Object newValue, Object oldValue) {
        if(isEmpty(newValue) && isEmpty(oldValue)) return false;
        else if( !isEmpty(newValue) && isEmpty(oldValue)) return true;
        else if( isEmpty(newValue) && !isEmpty(oldValue)) return true;
        else return newValue.equals(oldValue);
    }

    private Method getGetter(Method setter) {
        Method getter = cache.get(setter.getName());
        if(!isEmpty(getter)) {
            return getter;
        }
        String getterName = setter.getName().replaceFirst("set","get");

        try {
            getter = setter.getDeclaringClass().getMethod(getterName,null);
            synchronized (cache){
                cache.put(setter,getter);
                return getter;
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
