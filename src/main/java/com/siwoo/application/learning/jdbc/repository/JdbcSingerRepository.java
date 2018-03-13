package com.siwoo.application.learning.jdbc.repository;

import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerRepository implements SingerRepository {
    //private DataSource dataSource;
    //private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    /*
        * The PostConstruct annotation is used on a method that needs to be executed
        * after dependency injection is done to perform any initialization.
    */
    public JdbcSingerRepository(NamedParameterJdbcTemplate jdbcTemplate /*,JdbcTemplate jdbcTemplate*/ /*DataSource dataSource*/) {
        //this.dataSource = dataSource;
        //Don't do this. JdbcTemplate is thread safe and it can be shared
        //with all dao. This means you should inject jdbctemplate into your class
        //jdbcTemplate = new JdbcTemplate(dataSource);
       // this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init(){
        //System.out.println("PostConstruct Called");
        //Assert.notNull(dataSource,"DataSource must not be null");
        Assert.notNull(jdbcTemplate,"JdbcTemplate must not be null");
    }

    @Override
    public String findFullNameById(Long id){
        //no good, expensive connection called twice
        return findFirstNameById(id)+ " "+findLastNameById(id);
    }

    private static final String SQL_FIND_LASTNAME_BY_ID
            ="select last_name from singer where id = :singerId ";
    private static final String PARAM_SINGER_ID = "singerId";
    @Override
    public String findLastNameById(Long id) {
        //        Object[] args = new Object[]{id};
        //        return jdbcTemplate.queryForObject(SQL_FIND_LASTNAME_BY_ID,
        //                args,String.class);
        Map<String,Object> namedParams = new HashMap<>();
        namedParams.put(PARAM_SINGER_ID,id);
        return jdbcTemplate.queryForObject(SQL_FIND_LASTNAME_BY_ID,namedParams,String.class);
    }

    private static final String SQL_FIND_FIRSTNAME_BY_ID
            ="select first_name from singer where id = :singerId ";
    @Override
    public String findFirstNameById(Long id) {
        //        Object[] args = new Object[]{id};
        //        return jdbcTemplate.queryForObject(SQL_FIND_FIRSTNAME_BY_ID,
        //                args,String.class);
        Map<String,Object> namedParams = new HashMap<>();
        namedParams.put(PARAM_SINGER_ID,id);
        return jdbcTemplate.queryForObject(SQL_FIND_FIRSTNAME_BY_ID,namedParams,String.class);
    }


    // RowMapper objects are typically stateless and thus reusable;
    private static RowMapper<Singer> singerRowMapper = (rs,rowNum) -> {
        Singer singer = new JdbcSinger();
        singer.setId(rs.getLong(1));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    };
    private static final String SQL_FINDALL =
            "select id, first_name, last_name, birth_date from singer ";
    @Override
    public List<Singer> findAll() {
        return jdbcTemplate.query(SQL_FINDALL,singerRowMapper);
    }

    @Override
    public List<Singer> findByFirstname(String firstName) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void save(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void update(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void delete(Long singerId) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public List<Singer> findAllWithDetail() {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void insertWithDefail(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }

}
