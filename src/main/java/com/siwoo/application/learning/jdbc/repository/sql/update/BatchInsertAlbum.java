package com.siwoo.application.learning.jdbc.repository.sql.update;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class BatchInsertAlbum extends BatchSqlUpdate{
    private static final String SQL =
            "insert into album (singer_id, title, release_date) " +
                    "values(:singer_id, :title, :release_date) ";
    private static final int BATCH_SIZE = 10;

    public BatchInsertAlbum(DataSource ds) {
        super(ds, SQL);
        setParameters(new SqlParameter("singer_id", Types.INTEGER));
        setParameters(new SqlParameter("title", Types.VARCHAR));
        setParameters(new SqlParameter("release_date", Types.DATE));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
        setBatchSize(BATCH_SIZE);
    }

}
