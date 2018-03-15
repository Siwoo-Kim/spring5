package com.siwoo.application.learning.jdbc.repository.sql.query;

import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FindAllSingerByFirstName extends MappingSqlQuery<Singer> {
    private static final String SQL = "select id, first_name, last_name, birth_date from singer where first_name = :first_name ";
    public FindAllSingerByFirstName(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("first_name", Types.VARCHAR));
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
