package com.siwoo.application.learning.jdbc.repository;


import com.siwoo.application.learning.hibernate.entity.SingerEntity;
import com.siwoo.application.learning.jdbc.Singer;

import java.util.List;

public interface SingerRepository<T>{

    List<T> findAll();

    SingerEntity findById(Long id);

    List<T> findByFirstName(String firstName);

    String findFullNameById(Long id);

    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void save(T singer);

    void saveWithAlbum(T singer);

    boolean update(T singer);
    void delete(Long singerId);
    List<T> findAllWithAlbums();
    void insertWithDetail(T singer);
    
}
