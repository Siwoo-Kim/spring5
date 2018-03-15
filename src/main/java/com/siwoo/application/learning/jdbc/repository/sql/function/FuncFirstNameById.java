package com.siwoo.application.learning.jdbc.repository.sql.function;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class FuncFirstNameById extends SqlFunction<String>{
    private static final String FUNC_SQL = "select getFirstNameById(?)";

    public FuncFirstNameById(DataSource ds) {
        super(ds, FUNC_SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
