package com.siwoo.application.learning.aop.advice;

import com.siwoo.application.learning.aop.WorkerBean;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ProfilingInterceptor implements MethodInterceptor{
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if(validateJoinPoint(invocation)){
            StopWatch stopWatch = new StopWatch();
            stopWatch.start(invocation.getMethod().getName());
            Object result = invocation.proceed();
            stopWatch.stop();
            fetchTaskInfo(invocation,stopWatch);
            return result;
        }
        return invocation.proceed();
    }

    private void fetchTaskInfo(MethodInvocation invocation, StopWatch stopWatch) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        List args = Arrays.asList( invocation.getArguments() );

        System.out.println();
        System.out.println("Method summary : '"+method.getName()+"' ");
        System.out.println("Target: '"+target.getClass().getName()+"' ");

        if(args.size() != 0){
            System.out.printf("Arguments: ");
            args.stream().forEach(arg -> {
                System.out.printf(arg +", ");
            });
        }
        System.out.println("== Time to execute ==");
        System.out.println(stopWatch.prettyPrint());

    }

    private boolean validateJoinPoint(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        return target instanceof WorkerBean && "doWork".equals(method.getName()) ;
    }
}
