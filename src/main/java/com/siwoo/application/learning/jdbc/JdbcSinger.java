package com.siwoo.application.learning.jdbc;

import java.util.ArrayList;

public class JdbcSinger extends Singer {

    public boolean addAlbum(Album album){
        if(album == null){
            setAlbums(new ArrayList<>());
            getAlbums().add(album);
            return true;
        }else if(getAlbums().contains(album)){
            return false;
        }
        getAlbums().add(album);
        return true;
    }
}
