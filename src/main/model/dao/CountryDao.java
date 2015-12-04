package main.model.dao;

import main.model.domain.Country;

import java.sql.SQLException;

/**
 * Created by Максим on 04.12.2015.
 */
public interface CountryDao {

    public int createCountry(Country country) throws SQLException;
    public void deleteCountry(Country country);
    public void viewAll();
    public Country findCountry();
}
