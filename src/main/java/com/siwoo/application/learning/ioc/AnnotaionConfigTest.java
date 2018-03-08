package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.MessageProvider;
import com.siwoo.application.learning.common.MessageRenderer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class AnnotaionConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(IocAnnotationConfig.class);
//        Assert.notNull(c.getBean(MessageRenderer.class),"MessageRenderer must not be null");
//        Assert.notNull(c.getBean(MessageProvider.class),"MessageProvider must not be null");
//        c.getBean(MessageRenderer.class).render();
        Assert.notNull(c.getBean("messageRenderer",MessageRenderer.class),"MessageRenderer must not be null");
        Assert.notNull(c.getBean("messageProvider",MessageProvider.class),"MessageProvider must not be null");
        c.getBean("messageRenderer",MessageRenderer.class).render();
        c.getBean("scanedMessageRenderer",MessageRenderer.class).render();
    }
}
