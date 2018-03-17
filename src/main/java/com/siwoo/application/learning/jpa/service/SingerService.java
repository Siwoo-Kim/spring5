package com.siwoo.application.learning.jpa.service;

import com.siwoo.application.learning.jpa.entity.JpaSingerEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingerService {
    List<JpaSingerEntity> findAll();
    List<JpaSingerEntity> findAllWithAlbums();
    JpaSingerEntity findById(Long id);

    @Transactional(readOnly = false)
    JpaSingerEntity save(JpaSingerEntity entity);
    @Transactional(readOnly = false)
    void delete(JpaSingerEntity entity);
    List<JpaSingerEntity> findAllByNativeQuery();
}
