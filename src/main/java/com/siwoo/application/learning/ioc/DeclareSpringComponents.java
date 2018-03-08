package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/ioc-context.xml"));
        MessageRenderer messageRenderer = c.getBean("messageRenderer",MessageRenderer.class);
        Assert.notNull(messageRenderer,"messageRenderer must not be null");
        messageRenderer.render();
        MessageRenderer scanedMessageRenderer = c.getBean("scanedMessageRenderer",MessageRenderer.class);
        Assert.notNull(scanedMessageRenderer,"scanedMessageRenderer must not be null");
        scanedMessageRenderer.render();
        Assert.isTrue(messageRenderer!=scanedMessageRenderer,"Two instance must not be same");
    }
}
