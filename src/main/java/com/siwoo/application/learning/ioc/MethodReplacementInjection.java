package com.siwoo.application.learning.ioc;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class MethodReplacementInjection {
    /*
        The method replacement injection replace the implementation of any method on the beans.
        Consider when your are dealing (using) third part's library and you want to change specific
        method on the third party's code, then the method replacement is useful.
    */

    public static class ReplacementTarget{
        public String formatMessage(String msg){
            return "<p>"+msg+"</p>";
        }
        public String formatMessage(Object msg){
            return "<p>"+msg+"</p>";
        }
    }

    public static class FormatMessageReplacer implements MethodReplacer{
        private static final String targetMethodName = "formatMessage";
        @Override
        public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
            if(isFormatMessageMethod(method)){
                String msg = (String) args[0];
                return "<h1>"+msg+"</h1>";
            }else{
                throw new IllegalArgumentException("Unable to reimplement method "+method.getName());
            }
        }

        private boolean isFormatMessageMethod(Method method) {
            if(method.getParameterTypes().length != 1){
                return false;
            }
            if(!targetMethodName.equals(method.getName())){
                return false;
            }
            if(method.getReturnType() != String.class){
                return false;
            }
            if(method.getParameterTypes()[0] != String.class){
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/ioc-context.xml"));
        System.out.println( c.getBean("replacementTarget",ReplacementTarget.class).formatMessage("Hello Spring!") );
        System.out.println( c.getBean("standardTarget",ReplacementTarget.class).formatMessage("Hello Spring!") );
        Object obj = new String("Hello! Spring5!");
        System.out.println( c.getBean("replacementTarget",ReplacementTarget.class).formatMessage(obj) );
        System.out.println( c.getBean("standardTarget",ReplacementTarget.class).formatMessage(obj) );
    }

}
