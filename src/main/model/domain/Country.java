package main.model.domain;

/**
 * Created by Максим on 04.12.2015.
 */
public class Country {
    private int id_country;
    private String country_name;

    public Country(String country_name) {
        this.country_name = country_name;
    }

    public int getId_country() {
        return id_country;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id_country=" + id_country +
                ", country_name='" + country_name + '\'' +
                '}';
    }
}
