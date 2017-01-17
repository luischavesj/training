package com.prodigious.training.day4.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Luis Chaves on 1/16/2017 for Week 1 day 4 Exercise.
 */
public class DatabaseService {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/development?useSSL=false";

    private static final String USER = "developer";
    private static final String PASSWORD = "developer";
    private Connection connection;
    public DatabaseService() {
        try {
            Class.forName(DatabaseService.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseService.DB_URL, DatabaseService.USER, DatabaseService.PASSWORD);
    }

    protected PreparedStatement getStatement(String sqlStatement) throws SQLException {
        connection = getConnection();
        return connection.prepareStatement(sqlStatement);
    }

    protected void executeWithoutResultSet(PreparedStatement statement){
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
