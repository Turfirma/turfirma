package main.model.domain;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class Hotel {
    private int id_hotel;
    private String hotel_name;
    private int id_city;
    private int id_country;

    public Hotel(String hotel_name, int id_city, int id_country) {
        this.id_city = id_city;
        this.id_country = id_country;
        this.hotel_name = hotel_name;
    }

    public Hotel() {
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public int getId_city() {
        return id_city;
    }

    public int getId_country() {
        return id_country;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id_hotel=" + id_hotel +
                ", id_city=" + id_city +
                ", id_country=" + id_country +
                ", hotel_name='" + hotel_name + '\'' +
                '}';
    }
}
