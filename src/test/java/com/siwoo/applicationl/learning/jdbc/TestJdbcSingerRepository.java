package com.siwoo.applicationl.learning.jdbc;

import com.siwoo.application.learning.jdbc.config.JdbcConfig;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.siwoo.applicationl.learning.jdbc.TestPureJdbcSingerRepository.listSinger;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class TestJdbcSingerRepository {

    @Autowired
    SingerRepository jdbcSingerRepository;

    //No good test method
    //John Mayer, id = 1,
    @Test
    public void testFindNameById(){
        long searchId = 1l;
        String searchFirstName = "John";
        String searchLastName = "Mayer";

        String foundFirstName = jdbcSingerRepository.findFirstNameById(searchId);
        assertEquals(searchFirstName,foundFirstName);
        String foundLastName = jdbcSingerRepository.findLastNameById(searchId);
        assertEquals(searchLastName,foundLastName);
        String foundFullName = jdbcSingerRepository.findFullNameById(searchId);
        assertEquals(searchFirstName+" "+searchLastName,foundFullName);
    }


    @Test
    public void testFindAll(){
        listSinger.accept(jdbcSingerRepository.findAll());
        assertEquals(jdbcSingerRepository.findAll().size(),3);
    }
}
