package com.siwoo.applicationl.learning.jpa;

import com.siwoo.application.learning.jpa.config.JpaConfig;
import com.siwoo.application.learning.jpa.entity.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/spring/jpa/jpa-context.xml")
public class TestContext {

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext EntityManager entityManager;
    @Autowired PlatformTransactionManager transactionManager;

    /*xml config*/
    @Test @Transactional
    public void testContext(){
        test();
    }

    /*annotaion config*/
    @Test @Transactional @DirtiesContext
    public void testContext2(){
        ApplicationContext c = new AnnotationConfigApplicationContext(JpaConfig.class);
        entityManagerFactory = c.getBean(EntityManagerFactory.class);
        entityManager = entityManagerFactory.createEntityManager();
        transactionManager = c.getBean(PlatformTransactionManager.class);
        test();
    }


    private void test() {
        assertNotNull(entityManager);
        assertNotNull(entityManagerFactory);
        assertNotNull(transactionManager);
        TestEntity entity = new TestEntity();

        entity.setId(1);
        entityManager.persist(entity);
        TestEntity foundEntity = entityManager.find(TestEntity.class,entity.getId());
        assertNotNull(foundEntity);
    }
}
