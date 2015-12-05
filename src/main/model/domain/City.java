package main.model.domain;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class City {
    private int id_city;
    private int id_country;
    private String city_name;

    public City() {
    }

    public City(String city_name, int id_country) {
        this.city_name = city_name;
        this.id_country = id_country;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getId_city() {
        return id_city;
    }

    public int getId_country() {
        return id_country;
    }

    public String getCity_name() {
        return city_name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id_city=" + id_city +
                ", id_country=" + id_country +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}
