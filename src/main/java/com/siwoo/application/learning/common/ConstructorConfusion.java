package com.siwoo.application.learning.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

@Slf4j
public class ConstructorConfusion {
    //private String someValue;
    private Object someValue;
    public ConstructorConfusion(String someValue) {
        log.info("String constructor is called");
        Assert.hasText(someValue,"someValue must not be blank");
        this.someValue = someValue;
    }

    public ConstructorConfusion(Integer someValue) {
        log.info("Integer constructor is called");
        Assert.notNull(someValue,"someValue must not be null");
        this.someValue = someValue;
    }

    public Object getValue() {
        return someValue;
    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/ioc-context.xml"));
        Class<?> intConstSomeValueClass = c.getBean("intConstructorConfusion",ConstructorConfusion.class).getValue().getClass();
        Class<?> strConstSomeValueClass = c.getBean("strConstructorConfusion",ConstructorConfusion.class).getValue().getClass();
        Assert.isTrue(intConstSomeValueClass==Integer.class);
        Assert.isTrue(strConstSomeValueClass==String.class);

    }
}
