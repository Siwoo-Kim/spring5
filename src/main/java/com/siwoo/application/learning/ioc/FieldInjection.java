package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class FieldInjection {

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/ioc-context.xml"));
        c.getBean(Singer.class).sing();
    }
}
