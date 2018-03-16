package com.siwoo.applicationl.learning.jdbc;

import com.siwoo.application.learning.jdbc.Album;
import com.siwoo.application.learning.jdbc.JdbcAlbum;
import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import com.siwoo.application.learning.jdbc.config.JdbcConfig;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;

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

    @Test
    public void testFindAllWithAlbums(){
        listSinger.accept(jdbcSingerRepository.findAllWithAlbums());
        assertEquals(jdbcSingerRepository.findAll().size(),3);
    }

    @Test
    public void testFindByFirstName(){
        listSinger.accept(jdbcSingerRepository.findByFirstName("John"));
        assertEquals(jdbcSingerRepository.findByFirstName("John").size(),2);
        listSinger.accept(jdbcSingerRepository.findByFirstName("Eric"));
        assertEquals(jdbcSingerRepository.findByFirstName("Eric").size(),1);
    }

    @Test
    public void testSave(){
        Singer singer = new JdbcSinger();
        singer.setLastName("Kim");
        singer.setFirstName("Siwoo");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1989,3,4);
        singer.setBirthDate(new Date( calendar.getTime().getTime() ));
        jdbcSingerRepository.save(singer);

    }

    @Test
    public void testSaveWithAlbum(){
        JdbcSinger singer = new JdbcSinger();
        singer.setFirstName("Siwoo");
        singer.setLastName("Kim");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1989,3,4);
        singer.setBirthDate(new Date( calendar.getTime().getTime() ));

        Album album = new JdbcAlbum();
        album.setTitle("A");
        album.setReleaseDate(new Date(calendar.getTime().getTime()));
        singer.addAlbum(album);

        Album album2 = new JdbcAlbum();
        album2.setTitle("B");
        album2.setReleaseDate(new Date(calendar.getTime().getTime()));
        singer.addAlbum(album2);

        jdbcSingerRepository.saveWithAlbum(singer);

        listSinger.accept( jdbcSingerRepository.findAllWithAlbums() );

    }

//    @Test
//    public void testUpdate(){
//        Singer singer = jdbcSingerRepository.findByFirstName("John").get(0);
//        singer.setFirstName("Kim");
//        singer.setLastName("Siwoo");
//        assertEquals( jdbcSingerRepository.update(singer) , true);
//        listSinger.accept(jdbcSingerRepository.findAll());
//    }
}
