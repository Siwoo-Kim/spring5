package com.siwoo.applicationl.learning.jdbc;

import com.siwoo.application.learning.jdbc.config.JdbcConfig;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
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
@ContextConfiguration(locations = "classpath:/spring/jdbc/jdbc-context.xml")
public class TestDataSource {

    @Autowired
    DataSource dataSource;
    @Autowired
    SingerRepository singerRepository;

    @Test
    public void testSingerRepository(){
        assertNotNull(singerRepository);
    }

    @Test
    public void testDataSource(){
        assertNotNull(dataSource);
    }
    @Test @DirtiesContext
    public void testSingerRepository2(){
        ApplicationContext c = new AnnotationConfigApplicationContext(JdbcConfig.class);
        singerRepository = c.getBean(SingerRepository.class);
        testSingerRepository();
    }

    @Test @DirtiesContext
    public void testDataSource2(){
        ApplicationContext c = new AnnotationConfigApplicationContext(JdbcConfig.class);
        dataSource = c.getBean(DataSource.class);
        testDataSource();
    }
}
