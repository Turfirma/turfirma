package main.model.dao;

import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.*;

public class CountryDaoImpl implements CountryDao {

    @Override
    public int createCountry(Country country) throws SQLException {
        JDBCConnection connection = new JDBCConnection();
        Statement statement = connection.getConnection().createStatement();
        int result = statement.executeUpdate("INSERT INTO turfirma.country (country_name) VALUE ('" + country.getCountry_name() + "');");
        //JDBCConnection.getInstance().close();
        return result;
    }

    @Override
    public int deleteCountry(Country country) throws SQLException {
        JDBCConnection connection = new JDBCConnection();
        Statement statement = connection.getConnection().createStatement();
        if (statement==null) System.out.println("null");
        int result = statement.executeUpdate("DELETE FROM turfirma.country WHERE country_name = '" + country.getCountry_name() + "';");
        //JDBCConnection.getInstance().close();
        return result;
    }

    @Override
    public void viewAll() {

    }

    @Override
    public Country findCountry() {
        return null;
    }
}
