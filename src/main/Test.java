package main;

import main.model.dao.CityDaoImpl;
import main.model.dao.ClientDaoImpl;
import main.model.dao.CountryDao;
import main.model.dao.CountryDaoImpl;
import main.model.domain.City;
import main.model.domain.Client;
import main.model.domain.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) {
      /*  Country country = new Country("Georgia");
        CountryDao countryDao = new CountryDaoImpl();

        City city1 = new City("Los-Angeles",3);
        new CityDaoImpl().createCity(city1);*/

        /*Client client = new Client("a","b","da",1);
        new ClientDaoImpl().createClient(client);*/

        List<City> cities = new CityDaoImpl().getAll();

        for(City city: cities) {
            System.out.println(city);
        }

    }
}
