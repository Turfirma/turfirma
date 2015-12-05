package main.model.dao;

import main.model.domain.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */
public interface CountryDao {

    int createCountry(Country country) throws SQLException;

    int deleteCountry(Country country) throws SQLException;

    List<Country> getAll() throws SQLException;

    Country findCountry();
}
