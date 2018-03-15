package com.siwoo.application.learning.jdbc.repository.sql.update;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertSinger extends SqlUpdate{
    private static final String SQL
            ="insert into singer (first_name, last_name, birth_date) " +
            "values(:first_name, :last_name, :birth_date) ";

    public InsertSinger(DataSource dataSource) {
        super(dataSource,SQL);
        declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        declareParameter(new SqlParameter("birth_date", Types.DATE));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
