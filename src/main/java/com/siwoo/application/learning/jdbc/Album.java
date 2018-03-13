package com.siwoo.application.learning.jdbc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Getter @Setter @ToString
public abstract class Album implements Serializable{
    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

}
