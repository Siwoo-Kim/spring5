package com.siwoo.application.learning.jdbc.repository;

import com.siwoo.application.learning.jdbc.JdbcSinger;
import com.siwoo.application.learning.jdbc.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PureJdbcSingerRepository implements SingerRepository {

    static{
        Connection connection = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver"); Deprecated
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            Assert.notNull(connection,"Retrieve Connection failed");
        }catch (ClassNotFoundException | SQLException e){
            log.error("Error to retrieve Jdbc Driver Class");
        }finally {
            closeConnection(connection);
        }
    }

    private static final String username = "java";
    private static final String password = "java";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/spring5?useSSL=false";
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl,username,password);
    }
    private static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Closing connection failed");
            }
        }
    }

    private static String SQL_INSERT = "insert into singer (first_name, last_name, birth_date)" +
            " values (?,?,?) ";
    @Override
    public void save(Singer singer) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = getConnection();
            ps = c.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,singer.getFirstName());
            ps.setString(2,singer.getLastName());
            ps.setDate(3,singer.getBirthDate());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                singer.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            log.error("INSERT ERROR ");
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            closeConnection(c);
        }
    }

    @Override
    public void saveWithAlbum(Singer singer) {
        throw new UnsupportedOperationException();
    }

    private static String SQL_FINDALL = "select * from singer";
    @Override
    public List<Singer> findAll() {
        List<Singer> r = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        try {
            c = getConnection();
            ps = c.prepareStatement(SQL_FINDALL);
            rs = ps.executeQuery();
            while(rs.next()){
                Singer singer = new JdbcSinger();
                singer.setId(rs.getLong("id"));
                singer.setFirstName(rs.getString("first_name"));
                singer.setLastName(rs.getString("last_name"));
                singer.setBirthDate(rs.getDate("birth_date"));
                r.add(singer);
            }
            ps.close();
        } catch (SQLException e) {
            log.error("FINDALL ERROR ");
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            closeConnection(c);
        }
        return r;
    }

    private static final String SQL_DELETE = "delete from singer where id = ? ";
    @Override
    public void delete(Long singerId) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = getConnection();
            ps = c.prepareStatement(SQL_DELETE);
            ps.setLong(1,singerId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("DELETE ERROR ");
        }finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            closeConnection(c);
        }
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public String findFullNameById(Long id) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public String findLastNameById(Long id) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public String findFirstNameById(Long id) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public boolean update(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }


    @Override
    public List<Singer> findAllWithAlbums() {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void insertWithDetail(Singer singer) {
        throw new UnsupportedOperationException("not implement");
    }
}
