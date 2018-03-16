package com.siwoo.application.learning.hibernate.init;

import com.siwoo.application.learning.hibernate.entity.AlbumEntity;
import com.siwoo.application.learning.hibernate.entity.InstrumentEntity;
import com.siwoo.application.learning.hibernate.entity.SingerEntity;
import com.siwoo.application.learning.hibernate.repository.InstrumentRepository;
import com.siwoo.application.learning.jdbc.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;


    /*
        init database data.
        You can also use ApplicationListener
    */

@Slf4j
@Component
public class DBInitializer {
    @Autowired
    InstrumentRepository hibernateInstrumentRepository;
    @Autowired SingerRepository hibernateSingerRepository;

    @PostConstruct
    public void initDB(){
        log.info("Starting database initialization...");

        InstrumentEntity guitar = new InstrumentEntity();
        guitar.setInstrumentName("Guitar");
        hibernateInstrumentRepository.save(guitar);

        InstrumentEntity piano = new InstrumentEntity();
        piano.setInstrumentName("Piano");
        hibernateInstrumentRepository.save(piano);

        InstrumentEntity voice = new InstrumentEntity();
        voice.setInstrumentName("Voice");
        hibernateInstrumentRepository.save(voice);

        SingerEntity singer = new SingerEntity();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(LocalDate.now());
        singer.addInstrument(guitar);
        singer.addInstrument(piano);

        AlbumEntity album1 = new AlbumEntity();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(LocalDate.now());
        singer.addAlbum(album1);

        AlbumEntity album2 = new AlbumEntity();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(LocalDate.now());
        singer.addAlbum(album2);

        hibernateSingerRepository.save(singer);

        singer = new SingerEntity();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(LocalDate.now());
        singer.addInstrument(guitar);

        AlbumEntity album = new AlbumEntity();
        album.setTitle("From The Cradle");
        album.setReleaseDate(LocalDate.now());
        singer.addAlbum(album);

        hibernateSingerRepository.save(singer);

        singer = new SingerEntity();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(LocalDate.now());
        singer.addInstrument(guitar);

        hibernateSingerRepository.save(singer);
        log.info("Database initialization finished.");
        
    }

}
