package main.model.daoImpl;

import main.model.dao.CountryDao;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private final static String FIND_COUNTRY_BY_COUNTRY_NAME = "SELECT id_country FROM turfirma.country " +
            "WHERE country_name LIKE ?;";

    private JDBCConnection connection;

    @Override
    public int createCountry(Country country) {
        try {
            connection = new JDBCConnection();
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
            connection = new JDBCConnection();
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
            connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM turfirma.country GROUP BY id_country");
            while (resultSet.next()) {
                Country country = new Country();
                country.setId_country(resultSet.getInt(1));
                country.setCountry_name(resultSet.getString(2));
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
    public int findIdCountryByName(String countryName) {
        try {
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(FIND_COUNTRY_BY_COUNTRY_NAME);
            ps.setString(1, countryName);
            ResultSet set = ps.executeQuery();
            set.next();
            int i = set.getInt(1);
            connection.getConnection().close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
