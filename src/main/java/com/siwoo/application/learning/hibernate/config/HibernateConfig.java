package com.siwoo.application.learning.hibernate.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Configuration
@ComponentScan(value="com.siwoo.application.learning.hibernate",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class))
@PropertySource(value = "classpath:/properties/jdbc.properties")
@EnableTransactionManagement
public class HibernateConfig {

//    @Bean
//    public DataSource dataSource(){
//        try {
//            return new EmbeddedDatabaseBuilder()
//                    .setType(EmbeddedDatabaseType.H2)
//                    .addScripts("classpath:/sql/chapter7.schema.sql")
//                    .build();
//        } catch (Exception e) {
//            log.error("Failed to load datasource");
//            return null;
//        }
//    }

    @Value("${jdbc.username}") String dbUsername;
    @Value("${jdbc.password}") String dbPassword;
    @Value("${jdbc.driverClassName}") String driverClassName;
    @Value("${jdbc.url}") String dbUrl;

    @Bean
    public DataSource dataSource(){
        log.info(dbUsername);
        log.info(dbPassword);
        log.info(driverClassName);
        log.info(dbUrl);
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            return dataSource;
        } catch (Exception e) {
            log.error("Failed to load dataSource");
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.siwoo.application.learning.hibernate.entity");
        sessionFactoryBean.afterPropertiesSet(); //init
        return sessionFactoryBean.getObject();
    }


    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        /*hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");*/
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }
}
