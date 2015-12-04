package main.model.dao;

import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.*;

/**
 * Created by Максим on 04.12.2015.
 */
public class CountryDaoImpl implements CountryDao {


    @Override
    public int createCountry(Country country) throws SQLException {
        Statement statement = JDBCConnection.getInstance().createStatement();
        int result = statement.executeUpdate("INSERT INTO turfirma.country (country_name) VALUE ('" + country.getCountry_name() + "');");
        JDBCConnection.getInstance().close();
        return result;

    }

    @Override
    public void deleteCountry(Country country) {

    }

    @Override
    public void viewAll() {

    }

    @Override
    public Country findCountry() {
        return null;
    }
}
