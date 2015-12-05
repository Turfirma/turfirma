package main.model.dao;

import main.model.domain.Country;

import java.sql.SQLException;

/**
 * Created by Максим on 04.12.2015.
 */
public interface CountryDao {

    int createCountry(Country country) throws SQLException;

    int deleteCountry(Country country) throws SQLException;

    void viewAll();

    Country findCountry();
}
