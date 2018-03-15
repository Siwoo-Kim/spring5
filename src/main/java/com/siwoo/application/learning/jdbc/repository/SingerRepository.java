package com.siwoo.application.learning.jdbc.repository;


import com.siwoo.application.learning.jdbc.Singer;

import java.util.List;

public interface SingerRepository {

    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);

    String findFullNameById(Long id);

    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void save(Singer singer);

    void saveWithAlbum(Singer singer);

    boolean update(Singer singer);
    void delete(Long singerId);
    List<Singer> findAllWithAlbums();
    void insertWithDetail(Singer singer);
    
}
