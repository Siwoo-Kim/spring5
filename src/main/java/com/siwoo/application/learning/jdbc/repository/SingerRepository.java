package com.siwoo.application.learning.jdbc.repository;


import com.siwoo.application.learning.jdbc.Singer;

import java.util.List;

public interface SingerRepository {

    List<Singer> findAll();
    List<Singer> findByFirstname(String firstName);

    String findFullNameById(Long id);

    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void save(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);
    List<Singer> findAllWithDetail();
    void insertWithDefail(Singer singer);
    
}
