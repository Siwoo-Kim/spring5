package com.siwoo.application.learning.ioc;

import com.siwoo.application.learning.common.NotAComponent;
import com.siwoo.application.learning.common.Oracle;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

public class XmlConfigWithBeanFactory {
    //BeanFactory : the core of spring's dependency injection container
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("spring/ioc-context.xml"));
        Oracle oracle = (Oracle) factory.getBean("oracle");
        Assert.notNull(oracle,"Oracle must not be null");
        System.out.println(oracle.defineMeaningOfLife());
        Oracle bookworm = (Oracle) factory.getBean("bookworm");
        Assert.notNull(bookworm,"bookworm must not be null");
        System.out.println(bookworm.defineMeaningOfLife());
        Assert.isTrue(oracle==bookworm,"Two instance must be same");
        Oracle scanedOracle = (Oracle) factory.getBean("scanedOracle");
        Assert.notNull(bookworm,"scanedOracle must not be null");
        System.out.println(bookworm.defineMeaningOfLife());

        try{
            NotAComponent notAComponent = factory.getBean("notAComponent",NotAComponent.class);
            Assert.isTrue(notAComponent==null,"NotAComponent must be null");
        }catch (NoSuchBeanDefinitionException e){
            System.out.println(e.getMessage());
        }


    }
}
