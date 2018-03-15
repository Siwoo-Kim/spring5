package com.siwoo.application.learning.jdbc.repository.sql.query;

import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllSingers extends MappingSqlQuery<Singer> {
    private static String FIND_ALL = "select id, first_name, last_name, birth_date from singer ";

    public FindAllSingers(DataSource ds) {
        super(ds,FIND_ALL);
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new JdbcSinger();
        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    }
}
