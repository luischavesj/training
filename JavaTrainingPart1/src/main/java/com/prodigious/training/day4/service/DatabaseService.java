package com.prodigious.training.day4.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Luis Chaves on 1/16/2017 for Week 1 day 4 Exercise.
 */
public class DatabaseService {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/development";

    private static final String USER = "developer";
    private static final String PASSWORD = "developer";

    public DatabaseService() {
        try {
            Class.forName(DatabaseService.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseService.DB_URL, DatabaseService.USER, DatabaseService.PASSWORD);
    }
}
