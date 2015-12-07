package main.model.daoImpl;

import main.model.dao.CityDao;
import main.model.domain.City;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: turfirma
 *
 * Created by bo4ek
 * Date: 05.12.2015
 */
public class CityDaoImpl implements CityDao {

    private final static String FIND_CITY_BY_COUNTRY_ID = "SELECT id_city, city_name FROM turfirma.city " +
            "WHERE id_country = ?;";

    @Override
    public int createCity(City city, Country country) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.country (city_name, id_country) VALUE " +
                    "('" + city.getCity_name() + "');");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCity(City city) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM turfirma.city WHERE city_name LIKE '"
                    + city.getCity_name() + "';");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<City> getAll() {
        try {
            List<City> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.city");
            while (result.next()) {
                City city = new City();
                city.setId_city(result.getInt(1));
                city.setCity_name(result.getString(2));
                city.setId_country(result.getInt(3));
                list.add(city);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findCityByCountryId(int countryId) {
        try {
            List<City> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(FIND_CITY_BY_COUNTRY_ID);
            ps.setInt(1, countryId);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                City city = new City();
                city.setId_city(set.getInt(1));
                city.setCity_name(set.getString(2));
                list.add(city);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findCityByCountryName(String countryName) {
        int i = new CountryDaoImpl().findIdCountryByName(countryName);
        return findCityByCountryId(i);
    }
}
