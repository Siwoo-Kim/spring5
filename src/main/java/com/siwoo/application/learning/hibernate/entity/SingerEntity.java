package com.siwoo.application.learning.hibernate.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Getter @Setter @ToString(exclude = {"albums","instruments"})
@Entity(name="Singer") @Table(name = "singer")
@EqualsAndHashCode(of={"id","firstName"})
@NamedQueries({
        @NamedQuery(
                name = "Singer.findAllWithAlbums",
                /*
                    when you join fetch should you distinct.
                    otherwise the number of result will be entity *  number of association
                */
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i "),
        @NamedQuery(
                name = "Singer.findById",
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i " +
                        "where s.id = :id "),})
public class SingerEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Version
    private int version;

    @OneToMany(mappedBy = "singer",
    orphanRemoval = true,cascade = ALL)
    private Set<AlbumEntity> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name="singer_instrument",
    joinColumns = @JoinColumn(name="singer_id"),
    inverseJoinColumns = @JoinColumn(name="instrument_id"))
    private Set<InstrumentEntity> instruments = new HashSet<>();

    public void addAlbum(AlbumEntity albumEntity) {
        Assert.notNull(albumEntity,"Album must not be null");
        if(albumEntity.getSinger() != this){
            albumEntity.setSinger(this);
        }
        albums.add(albumEntity);
    }

    public void addInstrument(InstrumentEntity guitar) {
        Assert.notNull(guitar,"Instrument must not be null");
        guitar.getSingers().add(this);
        instruments.add(guitar);
    }
}
