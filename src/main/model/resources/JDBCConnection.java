package main.model.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by Максим on 04.12.2015.
 */
public class JDBCConnection {

    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/turfirma";
    private static final String USERNAME = "root";
    private static final String PASS = "root";

    public static Connection getInstance() {
        if (connection == null){
            try {
                System.out.println("Get Connection");
                connection = DriverManager.getConnection(URL,USERNAME,PASS);
                System.out.printf("Connection start");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection failed");
            }
        }
        return connection;
    }

    private JDBCConnection() {
    }
}
