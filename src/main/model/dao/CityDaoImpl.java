package main.model.dao;

import main.model.domain.City;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.Statement;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class CityDaoImpl implements CityDao {
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
        return 0;
    }

    @Override
    public List<City> getAll() {
        return null;
    }
}
