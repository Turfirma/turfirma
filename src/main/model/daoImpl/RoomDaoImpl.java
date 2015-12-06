package main.model.daoImpl;

import main.model.dao.RoomDao;
import main.model.domain.Room;
import main.model.resources.JDBCConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public class RoomDaoImpl implements RoomDao {

    @Override
    public int createRoom(Room room)  {
        try {
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.room (room_number,capacity,id_hotel) " +
                    "values " +
                    "(" +
                    room.getRoom_number() + "," +
                    room.getCapacity() + "," +
                    room.getId_hotel() + ");");
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
    public Room findRoom() {
        return null;
    }
}
