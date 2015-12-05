package main.model.daoImpl;

import main.model.dao.CountryDao;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    @Override
    public int createCountry(Country country) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.country (country_name) VALUE ('" + country.getCountry_name() + "');");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCountry(Country country) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM turfirma.country WHERE country_name = '" + country.getCountry_name() + "';");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Country> getAll() {
        try {
            List<Country> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.country");
            while (result.next()) {
                Country country = new Country();
                country.setId_country(result.getInt(1));
                country.setCountry_name(result.getString(2));
                list.add(country);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Country findCountry() {
        return null;
    }
}
