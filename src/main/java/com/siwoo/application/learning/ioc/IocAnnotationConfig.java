package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.HellowordMessageProvider;
import com.siwoo.application.learning.common.MessageProvider;
import com.siwoo.application.learning.common.MessageRenderer;
import com.siwoo.application.learning.common.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//ComponentScan("com.siwoo.application.learning.common")
@ImportResource("classpath:/spring/ioc-context.xml")
public class IocAnnotationConfig {

//    @Bean
//    public MessageRenderer messageRenderer(){
//        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
//        messageRenderer.setMessageProvider(messageProvider());
//        return messageRenderer;
//    }
//
//    @Bean
//    public MessageProvider messageProvider() {
//        return new HellowordMessageProvider();
//    }
}
