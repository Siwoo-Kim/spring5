package com.siwoo.application.learning.environment;

import com.siwoo.application.learning.common.FullName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class PropertyEditorDemo {

    @Getter @Setter @ToString
    public static class SampleBean{
        private FullName fullName;
    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/config-detail-context.xml"));
        System.out.println( c.getBean(SampleBean.class).getFullName() );
    }

}
