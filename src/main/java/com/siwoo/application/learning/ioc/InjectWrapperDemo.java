package com.siwoo.application.learning.ioc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Getter @Setter @ToString @Component("scannedInjectWrapper")
public class InjectWrapperDemo {
    @Value("#{injectSimpleSpelConfig.name}")
    private String name;
    @Value("#{injectSimpleSpelConfig.age}")
    private int age;
    @Value("#{injectSimpleSpelConfig.height}")
    private float height;
    @Value("#{injectSimpleSpelConfig.programmer}")
    private boolean programmer;
    @Value("#{injectSimpleSpelConfig.ageInSeconds}")
    private Long ageInSeconds;

    @Getter
    public static class InjectWrapperDemoConfig{
        private String name = "Siwoo Kim";
        private int age = 27;
        private float height = 173;
        private boolean programmer = true;
        private Long ageInSeconds = 841_011_112l;

    }
    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/ioc-context.xml"));
        InjectWrapperDemo bean = c.getBean("injectSimpleSpel",InjectWrapperDemo.class);
        Assert.notNull(bean,"Must not be null");
        System.out.println( bean );
        bean = c.getBean("scannedInjectWrapper",InjectWrapperDemo.class);
        Assert.notNull(bean,"Must not be null");
        System.out.println( bean );

    }
}
