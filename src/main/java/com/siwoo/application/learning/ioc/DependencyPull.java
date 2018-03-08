package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DependencyPull {

    public static void main(String[] args) {
        ApplicationContext c = new ClassPathXmlApplicationContext("classpath:/spring/ioc-context.xml");
        MessageRenderer messageRenderer = c.getBean("messageRenderer",MessageRenderer.class);
        Assert.notNull(messageRenderer,"Renderer must not null");
        messageRenderer.render();
    }
}
