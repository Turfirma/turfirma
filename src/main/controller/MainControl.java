package main.controller;

import main.model.daoImpl.CityDaoImpl;
import main.model.daoImpl.CountryDaoImpl;
import main.model.daoImpl.HotelDaoImpl;
import main.model.domain.City;
import main.model.domain.Country;
import main.model.domain.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class MainControl {

    //1.1
    public List<Country> getAllCountries() {
        return new CountryDaoImpl().getAll();
    }

    //1.2
    public List<City> getAllCities() {
        return new CityDaoImpl().getAll();
    }

    //2
    public List<Hotel> checkForHotelInCity(String cityName) {
        List<City> listAllCities = getAllCities();
        int id_city = 0;
        for (City city : listAllCities) {
            if (city.getCity_name().equals(cityName)) {
                id_city = city.getId_city();
            }
        }
        List<Hotel> listOfAll = new HotelDaoImpl().getAll();
        List<Hotel> listInCity = new ArrayList<>();
        for (Hotel hotel : listOfAll) {
            if (hotel.getId_city() == id_city) {
                listInCity.add(hotel);
            }
        }
        return listInCity;
    }

    //6
    public int howManyVisas() {
        return 0;
    }
}
