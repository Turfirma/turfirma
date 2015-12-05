package main.model.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/turfirma";
    private static final String USERNAME = "root";
    private static final String PASS = "root";

    public JDBCConnection() {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
