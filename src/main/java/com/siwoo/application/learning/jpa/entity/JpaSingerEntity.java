package com.siwoo.application.learning.jpa.entity;

import com.siwoo.application.learning.jdbc.Album;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="singer") @Table(name="singer")
@Getter @Setter @EqualsAndHashCode(of={"id","firstName","lastName"})
@NamedQueries({
        @NamedQuery(name = "Singer.findAll",
        query = "select s from singer s "),
        @NamedQuery(name = "Singer.findAllWithAlbum",
        query = "select distinct s from singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i "),
        @NamedQuery(name = "Singer.findById",
        query = "select distinct s from singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i " +
                "where s.id = :id "),
})
public class JpaSingerEntity implements Serializable{
    public static final String NAMEDQUERY_FINDALL ="Singer.findAll";
    public static final String NAMEDQUERY_FINDALL_WITH_ALBUM ="Singer.findAllWithAlbum";
    public static final String NAMEDQUERY_FIND_BY_ID ="Singer.findById";


    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Version
    private int version;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "singer",
    cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<JpaAlbumEntity> albums = new HashSet<>();

    /*bridge table*/
    @ManyToMany
    @JoinTable(name = "singer_instrument",
    joinColumns = @JoinColumn(name="singer_id"),
    inverseJoinColumns = @JoinColumn(name="instrument_id"))
    private Set<JpaInstrumentEntity> instruments = new HashSet<>();


    @Override
    public String toString() {
        return "JpaSingerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                ", birthDate=" + birthDate +
                '}';
    }

    public void addAlbum(JpaAlbumEntity album) {
        Assert.notNull(album,"album must not be null");
        if(album.getSinger() != this){
            album.setSinger(this);
        }
        albums.add(album);
    }
}
