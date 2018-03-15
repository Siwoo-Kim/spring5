package com.siwoo.application.learning.jdbc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Getter @Setter @ToString
public abstract class Singer implements Serializable{
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected List<Album> albums;



}
