package com.siwoo.application.learning.environment;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageEventDemo {

    @ToString
    public static class MessageEvent extends ApplicationEvent{
        @Getter
        private String msg;
        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MessageEvent(Object source,String msg) {
            super(source);
            this.msg = msg;
        }
    }

    public static class MessageEventListener implements ApplicationListener<MessageEvent>{
        @Override
        public void onApplicationEvent(MessageEvent event) {
            System.out.println("Recieved: "+event);
        }
    }

    public static class MessagePublisher implements ApplicationContextAware{
        private ApplicationContext c;
        public void emit(String message){
            c.publishEvent(new MessageEvent(this,message));
        }
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            c = applicationContext;
        }
    }

    public static void main(String[] args) {
        ApplicationContext c = new ClassPathXmlApplicationContext("spring/config-detail-context.xml");
        MessagePublisher publisher = c.getBean(MessagePublisher.class);
        for(int i=0;i<5;i++)
            publisher.emit("Emit event"+i);
    }
}
