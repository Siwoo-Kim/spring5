package com.siwoo.application.learning.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
/*
    Enables Spring's annotation-driven transaction management capability.
    어노테이션 선언적 트랜잭션기능 활성화
*/
@EnableTransactionManagement
@ComponentScan(
        value="com.siwoo.application.learning.jpa",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = Configuration.class))
public class JpaConfig {

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:/sql/chapter7.schema.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        /*
         * FactoryBean that creates a JPA
         * EntityManagerFactory according to JPA's standard
         * containerbootstrap contract. This is the most powerful way to set
         *  ( JEE Environment )
        */
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.siwoo.application.learning.jpa.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProps());
        factoryBean.afterPropertiesSet(); //init required;
        return factoryBean.getNativeEntityManagerFactory();
    }

    private Properties jpaProps() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        //hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        //hibernateProp.put("hibernate.hbm2ddl.auto", "create");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }
}
