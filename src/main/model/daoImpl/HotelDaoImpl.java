package main.model.daoImpl;

import main.model.dao.HotelDao;
import main.model.domain.Hotel;
import main.model.resources.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: turfirma
 *
 * Created by bo4ek
 * Date: 05.12.2015
 */
public class HotelDaoImpl implements HotelDao {

    private final static String GET_INFO_COUNTRY_CITY = "SELECT country_name,city_name FROM turfirma.hotels " +
            "INNER JOIN turfirma.city ON hotels.id_city = city.id_city " +
            "INNER JOIN turfirma.country ON city.id_country = country.id_country WHERE id_hotel = ?;";

    private final static String FIND_HOTEL_ID_AND_NAME = "SELECT id_hotel, hotel_name FROM hotels WHERE id_city = ?;";

    private JDBCConnection connection;

    @Override
    public int createHotel(Hotel hotel) {
        try {
            connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.hotels (hotel_name, id_country, id_city) VALUES " +
                    "('" + hotel.getHotel_name()
                    + "','" + hotel.getId_country()
                    + "','" + hotel.getId_city()
                    + "');");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteHotel(Hotel hotel) {
        try {
            connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM turfirma.hotels WHERE hotel_name LIKE '"
                    + hotel.getHotel_name()
                    + "' AND id_country = "
                    + hotel.getId_country()
                    + " AND id_city = "
                    + hotel.getId_city()
                    + ";");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Hotel> getAll() {
        try {
            List<Hotel> list = new ArrayList<>();
            connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.hotels");
            while (result.next()) {
                Hotel hotel = new Hotel();
                hotel.setId_hotel(result.getInt(1));
                hotel.setHotel_name(result.getString(2));
                hotel.setId_country(result.getInt(3));
                hotel.setId_city(result.getInt(4));
                list.add(hotel);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCountryCityById(int iD) {
        try {
            String allInfo = null;
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(GET_INFO_COUNTRY_CITY);
            ps.setInt(1, iD);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                allInfo = " " + resultSet.getString(1) + " | "
                        + resultSet.getString(2) + " | ";
            }
            connection.getConnection().close();
            return allInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hotel> findHotelByCityId(int cityId) {
        try {
            List<Hotel> list = new ArrayList<>();
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(FIND_HOTEL_ID_AND_NAME);
            ps.setInt(1, cityId);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                Hotel hotel = new Hotel();
                hotel.setId_hotel(set.getInt(1));
                hotel.setHotel_name(set.getString(2));
                list.add(hotel);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
