package com.siwoo.application.learning.environment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FactoryBeanDemo {

    @Setter @Getter
    public static class MessageDigestFactoryBean implements FactoryBean<MessageDigest>{

        private String algorithmName = "MD5";
        private MessageDigest messageDigest = null;

        @Override
        public MessageDigest getObject() throws Exception {
            return messageDigest;
        }

        @Override
        public Class<?> getObjectType() {
            return MessageDigest.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }

        public void app_init() throws NoSuchAlgorithmException {
            messageDigest = MessageDigest.getInstance(algorithmName);
        }
    }

    @Setter
    public static class MessageDigester{
        private MessageDigest digest1;
        private MessageDigest digest2;

        public void digest(String msg){
            System.out.println("Using digest1");
            digest1(msg,digest1);
            System.out.println("Using digest2");
            digest1(msg,digest2);
        }

        private void digest1(String msg, MessageDigest digest) {
            System.out.println("Using algorithm: "+digest.getAlgorithm());
            digest.reset();
            byte[] bytes = msg.getBytes();
            byte[] encap = digest.digest(bytes);
            System.out.println(encap);
        }
    }

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("spring/config-detail-context.xml"));
        c.getBean(MessageDigester.class).digest("Hello spring!");
    }
}
