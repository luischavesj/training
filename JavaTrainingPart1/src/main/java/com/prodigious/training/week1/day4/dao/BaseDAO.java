package com.prodigious.training.week1.day4.dao;

import org.apache.commons.dbcp.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
public class BaseDAO {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/development?useSSL=false";

    private static final String USER = "developer";
    private static final String PASSWORD = "developer";
    private static Connection connection;

    private static BasicDataSource basicDataSource;

    public BaseDAO() throws NamingException {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(BaseDAO.DRIVER_CLASS);
        basicDataSource.setUsername(BaseDAO.USER);
        basicDataSource.setPassword(BaseDAO.PASSWORD);
        basicDataSource.setUrl(BaseDAO.DB_URL);
    }

    private static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    protected PreparedStatement getStatement(String sqlStatement) throws SQLException {
        connection = getConnection();
        return connection.prepareStatement(sqlStatement);
    }

    protected void executeWithoutResultSet(PreparedStatement statement) throws SQLException{
        try {
            statement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }
}
