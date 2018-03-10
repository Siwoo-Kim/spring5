package com.siwoo.application.learning.ioc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;

@Getter @Setter @ToString @Component("scannedCollectionInjection")
public class CollectionInjection {
    @Autowired(required =false) @Qualifier("map")
    private Map<String,Object> map;
    @Autowired(required =false) @Qualifier("props")
    private Properties prop;
    @Autowired(required =false) @Qualifier("set")
    private Set set;
    @Autowired(required =false) @Qualifier("list")
    private List list;

    public static void main(String[] args) {
        ApplicationContext c = new GenericXmlApplicationContext(new ClassPathResource("/spring/ioc-context.xml"));
        //Assert.notNull(c.getBean(CollectionInjection.class),"Must not be null");
        //c.getBean(CollectionInjection.class).displayInfo();
        Assert.notNull(c.getBean("injectCollection",CollectionInjection.class),"Must not be null");
        c.getBean("injectCollection",CollectionInjection.class).displayInfo();
        Assert.notNull(c.getBean("scannedCollectionInjection",CollectionInjection.class),"Must not be null");
        c.getBean("scannedCollectionInjection",CollectionInjection.class).displayInfo();

    }

    Consumer<Map.Entry> listEntry = entry ->{
        System.out.println("Key: "+entry.getKey()+", value: "+entry.getValue());
    };
    Consumer<?> listObject = object ->{
        System.out.println("Value: "+object);
    };

    public void displayInfo(){
        System.out.println("\nMap: ");
        map.entrySet().stream().forEach(listEntry);
        System.out.println("\nProperties: ");
        prop.entrySet().stream().forEach(listEntry);
        System.out.println("\nSet: ");
        set.stream().forEach(listObject);
        System.out.println("\nList: ");
        list.stream().forEach(listObject);
    }

}
