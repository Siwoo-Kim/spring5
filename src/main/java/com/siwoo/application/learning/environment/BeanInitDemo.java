package com.siwoo.application.learning.environment;

import com.siwoo.application.learning.common.Singer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;

public class BeanInitDemo {

    public static class SingerInitAnnotation extends Singer{

        @Override //@PostConstruct
        public void app_init() {
            System.out.println("Initializing Bean with annotation");
            super.app_init();
        }
    }

    @Configuration
    public static class SingerInitConfiguration{

        @Bean(initMethod = "app_init") @Lazy
        Singer singer(){
            return new SingerInitAnnotation();
        }

    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/config-detail-context.xml"));
        Singer singer1 = c.getBean("singer1",Singer.class);
        Singer singer2 = c.getBean("singer2",Singer.class);
        Singer singer3 = c.getBean("singer3",Singer.class);

        c = new AnnotationConfigApplicationContext(SingerInitConfiguration.class);
        //singer1 = c.getBean(Singer.class);

    }


}
