package com.siwoo.applicationl.learning.jdbc;

import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import com.siwoo.application.learning.jdbc.repository.PureJdbcSingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class TestPureJdbcSingerRepository {
    private static PureJdbcSingerRepository singerRepository = new PureJdbcSingerRepository();

    public static Consumer<List<Singer>> listSinger = singers -> {
        singers.stream().forEach(singer -> {
            log.info(singer.toString());
            if(singer.getAlbums() != null && singer.getAlbums().size() > 0){
                singer.getAlbums().forEach(album -> {
                    log.info(album.toString());
                });
            }
        });

    };

    @Test
    public void testList(){
        assertNotNull(singerRepository);

        listSinger.accept(singerRepository.findAll());

        int prevSize = singerRepository.findAll().size();
        Singer singer = new JdbcSinger();
        singer.setFirstName("Siwoo");
        singer.setLastName("Kim");
        Calendar birthDate= Calendar.getInstance();
        birthDate.set(1989, 3, 4);
        singer.setBirthDate(new Date(birthDate.getTime().getTime()));
        singerRepository.save(singer);

        int nextSize = singerRepository.findAll().size();
        assertEquals(prevSize+1,nextSize);
        listSinger.accept(singerRepository.findAll());

        singerRepository.delete(singer.getId());
        nextSize = singerRepository.findAll().size();
        assertEquals(prevSize,nextSize);
    }
}
