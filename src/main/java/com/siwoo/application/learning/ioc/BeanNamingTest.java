package com.siwoo.application.learning.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class BeanNamingTest {

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/ioc-context.xml"));
        Map<String,String> beans = c.getBeansOfType(String.class);
        beans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));
        String s1 = c.getBean("siwooKim",String.class);
        String s2 = c.getBean("sw",String.class);
        String s3 = c.getBean("siwooK",String.class);
        String s4 = c.getBean("saewoo",String.class);
        Assert.isTrue(s1==s2,"Must be same instance");
        Assert.isTrue(s1==s3,"Must be same instance");
        Assert.isTrue(s1==s4,"Must be same instance");

    }
}
