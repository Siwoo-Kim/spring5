package com.siwoo.application.learning.environment;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

@Slf4j
public class BeanDestroyDemo {
    /*
    @Getter @Setter
    public static class DestructiveBean implements InitializingBean{
        private File file;
        private String filePath;
        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("Initializing Bean");
            Assert.hasText(filePath,"FilePath must not be null");
            file = new File(filePath);
            file.createNewFile();
            log.info("File created? : "+file.exists());
        }

        public void app_destroy(){
            log.info("Destroying Bean");
            if(!file.delete()){
                log.error("ERROR:: failed to delete file");
            }
            log.info("File deleted? : "+!file.exists());
        }
    }*/
    /*
    @Getter @Setter
    public static class DestructiveBean implements InitializingBean,DisposableBean{
        private File file;
        private String filePath;

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("Initializing Bean");
            Assert.hasText(filePath,"filePath must not be null");
            file = new File(filePath);
            file.createNewFile();
            log.info("File created? : "+file.exists());
        }

        @Override
        public void destroy() throws Exception {
            log.info("Destroying Bean");
            if(!file.delete()){
                log.error("ERROR:: failed to delete file");
            }
            log.info("File deleted? : "+!file.exists());
        }
    }
    */
    @Getter @Setter
    public static class DestructiveBean {
        private File file;
        private String filePath;

        @PostConstruct
        public void testing_init() throws IOException {
            log.info("Initializing Bean");
            Assert.hasText(filePath,"filePath must not be null");
            file = new File(filePath);
            file.createNewFile();
            log.info("File created? : "+file.exists());
        }

        @PreDestroy
        public void testing_destroy(){
            log.info("Destroying Bean");
            if(!file.delete()){
                log.error("ERROR:: failed to delete file");
            }
            log.info("File deleted? : "+!file.exists());
        }
    }

    @Configuration
    public static class DemoConfiguration{

        @Bean(initMethod = "testing_init",destroyMethod = "testing_destroy") @Lazy
        DestructiveBean bean(
                @Value("#{systemProperties['java.io.tmpdir']}#{systemProperties['file.seperator']}test.txt")
                        String filePath){
            DestructiveBean bean = new DestructiveBean();
            log.info(filePath);
            bean.setFilePath(filePath);
            return bean;
        }
    }

    public static void main(String[] args) {
        //GenericXmlApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/config-detail-context.xml"));
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(DemoConfiguration.class);
        DestructiveBean bean = c.getBean(DestructiveBean.class);
//        System.out.println("Calling destroy");
//        bean.app_destroy();
//        System.out.println("Callign destroy");
        c.close();
        //c.destroy();
    }
}
