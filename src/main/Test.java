package main;

import main.model.dao.CountryDao;
import main.model.dao.CountryDaoImpl;
import main.model.domain.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) {
        Country country = new Country("Georgia");
        CountryDao countryDao = new CountryDaoImpl();
        List<Country> list = countryDao.getAll();
    }
}
