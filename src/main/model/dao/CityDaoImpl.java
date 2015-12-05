package main.model.dao;

import main.model.domain.City;
import main.model.resources.JDBCConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class CityDaoImpl implements CityDao {
    @Override
    public int createCity(City city) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.city (city_name, id_country) VALUES " +
                    "('" + city.getCity_name() + "','" + city.getId_country() + "');");
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
}
