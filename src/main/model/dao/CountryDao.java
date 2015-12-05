package main.model.dao;

import main.model.domain.Country;

import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */
public interface CountryDao {

    int createCountry(Country country);

    int deleteCountry(Country country);

    List<Country> getAll();

    Country findCountry();
}
