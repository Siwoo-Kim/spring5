package com.siwoo.application.learning.jdbc.repository;

import com.siwoo.application.learning.hibernate.entity.SingerEntity;
import com.siwoo.application.learning.jdbc.Album;
import com.siwoo.application.learning.jdbc.JdbcAlbum;
import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import com.siwoo.application.learning.jdbc.repository.sql.function.FuncFirstNameById;
import com.siwoo.application.learning.jdbc.repository.sql.query.FindAllSingerByFirstName;
import com.siwoo.application.learning.jdbc.repository.sql.query.FindAllSingers;
import com.siwoo.application.learning.jdbc.repository.sql.update.BatchInsertAlbum;
import com.siwoo.application.learning.jdbc.repository.sql.update.InsertSinger;
import com.siwoo.application.learning.jdbc.repository.sql.update.UpdateSinger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;


@Slf4j
@Repository("jdbcSingerRepository")
public class JdbcSingerRepository implements SingerRepository<Singer> {
    //private DataSource dataSource;
    //private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Resource(name = "dataSource")
    private DataSource dataSource;
    private SqlQuery findAllSingers;
    private SqlQuery findAllSingerByFirstName;
    private SqlUpdate updateSinger;
    private SqlUpdate insertSinger;
    private BatchSqlUpdate insertAlbum;
    private SqlFunction<String> funcFirstNameById;

    /*
        * The PostConstruct annotation is used on a method that needs to be executed
        * after dependency injection is done to perform any initialization.
    */
    public JdbcSingerRepository(NamedParameterJdbcTemplate jdbcTemplate /*,JdbcTemplate jdbcTemplate*/ /*DataSource dataSource*/) {
        //this.dataSource = dataSource;
        //Don't do this. JdbcTemplate is thread safe and it can be shared
        //with all dao. This means you should inject jdbctemplate into your class
        //jdbcTemplate = new JdbcTemplate(dataSource);
       // this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init(){
        //System.out.println("PostConstruct Called");
        //Assert.notNull(dataSource,"DataSource must not be null");
        Assert.notNull(jdbcTemplate,"JdbcTemplate must not be null");
        Assert.notNull(dataSource,"DataSource must not be null");
        findAllSingers = new FindAllSingers(dataSource);
        findAllSingerByFirstName = new FindAllSingerByFirstName(dataSource);
        updateSinger = new UpdateSinger(dataSource);
        insertSinger = new InsertSinger(dataSource);
        insertAlbum = new BatchInsertAlbum(dataSource);
        funcFirstNameById = new FuncFirstNameById(dataSource);
    }

    @Override
    public String findFullNameById(Long id){
        //no good, expensive connection called twice
        return findFirstNameById(id)+ " "+findLastNameById(id);
    }

    private static final String SQL_FIND_LASTNAME_BY_ID
            ="select last_name from singer where id = :singerId ";
    private static final String PARAM_SINGER_ID = "singerId";
    @Override
    public String findLastNameById(Long id) {
        //        Object[] args = new Object[]{id};
        //        return jdbcTemplate.queryForObject(SQL_FIND_LASTNAME_BY_ID,
        //                args,String.class);
        Map<String,Object> namedParams = new HashMap<>();
        namedParams.put(PARAM_SINGER_ID,id);
        return jdbcTemplate.queryForObject(SQL_FIND_LASTNAME_BY_ID,namedParams,String.class);
    }


   private static final String SQL_FIND_FIRSTNAME_BY_ID
            ="select first_name from singer where id = :singerId ";
    @Override
    public String findFirstNameById(Long id) {
        //        Object[] args = new Object[]{id};
        //        return jdbcTemplate.queryForObject(SQL_FIND_FIRSTNAME_BY_ID,
        //                args,String.class);
        //Map<String,Object> namedParams = new HashMap<>();
        //namedParams.put(PARAM_SINGER_ID,id);
        //return jdbcTemplate.queryForObject(SQL_FIND_FIRSTNAME_BY_ID,namedParams,String.class);
        return funcFirstNameById.execute(id).get(0);
    }

    // RowMapper objects are typically stateless and thus reusable;
    private static RowMapper<Singer> singerRowMapper = (rs,rowNum) -> {
        Singer singer = new JdbcSinger();
        singer.setId(rs.getLong(1));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    };


    private static final String SQL_FINDALL =
            "select id, first_name, last_name, birth_date from singer ";
    @Override
    public List<Singer> findAll() {
        //return jdbcTemplate.sql(SQL_FINDALL,singerRowMapper);
        return findAllSingers.execute();
    }

    @Override
    public SingerEntity findById(Long id) {
        throw new UnsupportedOperationException();
    }


    private static final String SQL_FINDALL_WITH_ALBUMS
            =
            "SELECT s.id, s.first_name, s.last_name, s.birth_date, " +
                    "a.id as album_id, a.title, a.release_date from singer s " +
                    "left join album a on s.id = a.singer_id";

    /*
        RowMapper - Singer Object Mapping
        ResultSetExtractor - More complicated Object Mapping
        ResultsetExtractor you will need to iterate through the result set yourself,
        say in while loop
    */

    private static String SQL_FINDALL_WTIH_ALBUMS =
            "select s.id, s.first_name, s.last_name, s.birth_date, " +
                    "a.id as album_id, a.title, a.release_date from singer s " +
                    "left join album a on s.id = a.singer_id ";

    ResultSetExtractor<List<Singer>> singerJoinAlbumExtractor = rs -> {
      Map<Long, Singer> cache = new HashMap<>();
      while (rs.next()){
          Long singerId = rs.getLong("id");
          Singer singer = cache.get(singerId);
          if(singer==null){
              singer = new JdbcSinger();
              singer.setFirstName(rs.getString("first_name"));
              singer.setLastName(rs.getString("last_name"));
              singer.setBirthDate(rs.getDate("birth_date"));
              cache.put(singerId,singer);
          }
          Long albumId = rs.getLong("album_id");
          if(albumId != 0){
              Album album = new JdbcAlbum();
              album.setSingerId(singerId);
              album.setTitle(rs.getString("title"));
              album.setReleaseDate(rs.getDate("release_date"));
              ((JdbcSinger)singer).addAlbum(album);
          }
      }
      return new ArrayList<>(cache.values());
    };
    @Override
    public List<Singer> findAllWithAlbums() {
        return jdbcTemplate.query(SQL_FINDALL_WITH_ALBUMS,singerJoinAlbumExtractor);
    }


    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String,Object> params = new HashMap<>();
        params.put("first_name",firstName);
        return findAllSingerByFirstName.executeByNamedParam(params);
    }


    @Override
    public void save(Singer singer) {
        Map<String,Object> params = new HashMap<>();
        params.put("first_name",singer.getFirstName());
        params.put("last_name",singer.getLastName());
        params.put("birth_date",singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(params,keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        log.info(singer.toString()+" is inserted");
    }

    private Singer save_and_return(Singer singer) {
        save(singer);
        return singer;
    }



    @Override
    public void saveWithAlbum(Singer singer){
        save(singer);
        List<Album> albums = singer.getAlbums();
        if(!ObjectUtils.isEmpty(albums)){
            for(Album album: albums){
                Map<String,Object> params = new HashMap<>();
                params.put("singer_id",singer.getId());
                params.put("title",album.getTitle());
                params.put("release_date",album.getReleaseDate());
                KeyHolder keyHolder = new GeneratedKeyHolder();
                insertAlbum.updateByNamedParam(params,keyHolder);
                album.setId(keyHolder.getKey().longValue());
            }
        }
        insertAlbum.flush();
    }
    @Override
    public boolean update(Singer singer) {
        Map<String,Object> params = new HashMap<>();
        params.put("first_name",singer.getFirstName());
        params.put("last_name",singer.getLastName());
        params.put("birth_date",singer.getBirthDate());
        params.put("id",singer.getId());
        int result = updateSinger.updateByNamedParam(params);
        return result > 0;
    }

    @Override
    public void delete(Long singerId) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void insertWithDetail(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }

}
