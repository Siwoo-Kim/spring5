package com.siwoo.application.learning.environment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;

public class MessageSourceDemo {

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/config-detail-context.xml"));
        Locale eng = Locale.ENGLISH;
        Locale ger = new Locale("de","DE");

        System.out.println( c.getMessage("test.msg",null,eng) );
        System.out.println( c.getMessage("test.msg",null,ger) );
        System.out.println( c.getMessage("test.name",new Object[]{"Siwoo","Kim"},eng));
        System.out.println( c.getMessage("test.name",new Object[]{"Siwoo","Kim"},ger));
    }
}
