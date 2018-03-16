package com.siwoo.applicationl.learning.hibernate;

import com.siwoo.application.learning.hibernate.config.HibernateConfig;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/spring/hibernate/hibernate-context.xml")
public class TestContext {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource(){
        assertNotNull(dataSource);
    }

    @Test @DirtiesContext
    public void testAnnotation(){
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        assertNotNull(context);
        assertNotNull(context.getBean(SessionFactory.class));
    }
}
