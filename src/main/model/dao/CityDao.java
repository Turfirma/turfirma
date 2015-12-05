package main.model.dao;

import main.model.domain.City;
import main.model.domain.Country;

import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public interface CityDao {
    int createCity(City city, Country country);
    int deleteCity(City city);
    List<City> getAll();
}
