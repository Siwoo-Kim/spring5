package com.siwoo.application.learning.jdbc.repository.sql.update;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateSinger extends SqlUpdate {
    private static String SQL = "update singer set first_name = :first_name, " +
            "last_name = :last_name, birth_date = :birth_date " +
            "where id = :id";

    public UpdateSinger(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        declareParameter(new SqlParameter("birth_date", Types.DATE));
        declareParameter(new SqlParameter("id",Types.INTEGER));
    }

}
