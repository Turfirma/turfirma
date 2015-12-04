package main;

import main.model.dao.CountryDao;
import main.model.dao.CountryDaoImpl;
import main.model.domain.Country;

import java.sql.SQLException;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) throws SQLException {
        Country country = new Country("Georgia");
        CountryDao countryDao = new CountryDaoImpl();
        int check = countryDao.createCountry(country);
        if (check == 1) System.out.printf("Good");
        else System.out.printf("Bad");
    }


}
