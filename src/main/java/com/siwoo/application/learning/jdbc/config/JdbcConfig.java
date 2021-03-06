package com.siwoo.application.learning.jdbc.config;

import com.siwoo.application.learning.jdbc.repository.JdbcSingerRepository;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


import javax.sql.DataSource;
import java.sql.Driver;

@Slf4j
@Configuration
@ComponentScan("com.siwoo.application.learning.jdbc")
@PropertySource("classpath:/properties/jdbc.properties")
public class JdbcConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
    /*
        @Bean
        public DataSource dataSource(){
            try {
                return new EmbeddedDatabaseBuilder()
                        .setType(EmbeddedDatabaseType.H2)
                        .addScripts("classpath:/sql/chapter6.schema.sql")
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error to build embedded database engine");
                return null;
            }
        }
    */
    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource(){
        try {
            //System.out.println(driverClassName);
            //System.out.println(url);
            //System.out.println(username);
            //System.out.println(password);
            BasicDataSource dataSource = new BasicDataSource();
            //Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e ){
            log.error("Error to build pooling datasource engine");
            return null;
        }
    }


}
