package main.model.daoImpl;

import main.model.dao.RoomDao;
import main.model.domain.Room;
import main.model.resources.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public class RoomDaoImpl implements RoomDao {

    private final static String QUERY_GET_COUNTRY_CITY_HOTEL_BY_ID = "SELECT country_name,city_name,hotel_name,room_number  " +
            "FROM turfirma.room  " +
            "INNER JOIN turfirma.hotels ON room.id_hotel = hotels.id_hotel " +
            "INNER JOIN turfirma.city ON hotels.id_city = city.id_city " +
            "INNER JOIN turfirma.country ON hotels.id_country = country.id_country WHERE id_room = ? ; ";

    @Override
    public int createRoom(Room room)  {
        try {
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("SELECT country_name,city_name,hotel_name,room_number  " +
                    "FROM turfirma.room \n" +
                    "INNER JOIN turfirma.hotels ON room.id_hotel = hotels.id_hotel " +
                    "INNER JOIN turfirma.city ON hotels.id_city = city.id_city " +
                    "INNER JOIN turfirma.country ON hotels.id_country = country.id_country WHERE id_room = 3 ;");
            jdbcConnection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRoom(Room room) {
        try{
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM turfirma.room " +
                    "WHERE room_number = " + room.getRoom_number() +
                    " and capacity = " + room.getCapacity() +
                    " and id_hotel = " + room.getId_hotel() + " ;");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Room> getAll() {
        try {
            List<Room> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.room");
            while (result.next()) {
                Room room = new Room();
                room.setId_room(result.getInt(1));
                room.setRoom_number(result.getInt(2));
                room.setCapacity(result.getInt(3));
                room.setId_hotel(result.getInt(4));
                list.add(room);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room getRoomById(int iD) {
        try {
            Room room = new Room();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.room WHERE id_room = " + iD + ";");
            while (result.next()) {
                room.setId_room(result.getInt(1));
                room.setRoom_number(result.getInt(2));
                room.setCapacity(result.getInt(3));
                room.setId_hotel(result.getInt(4));
            }
            connection.getConnection().close();
            return room;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCountrCityHotelbyId(int iD) {
        try {
            String allInfo = null;
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(QUERY_GET_COUNTRY_CITY_HOTEL_BY_ID);
            preparedStatement.setInt(1,iD);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allInfo = " " + resultSet.getString(1) + " | "
                        + resultSet.getString(2) + " | "
                        + resultSet.getString(3) + " | "
                        + resultSet.getInt(4) + " | ";
            }
            connection.getConnection().close();
            return allInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room findRoom() {
        return null;
    }
}
