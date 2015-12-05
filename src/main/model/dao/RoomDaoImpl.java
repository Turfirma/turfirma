package main.model.dao;

import main.model.domain.Room;
import main.model.resources.JDBCConnection;

import java.sql.SQLException;
import java.sql.Statement;
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
            int result = statement.executeUpdate("INSERT INTO turfirma.room (room_number,capacity,id_hotel) values " +
                    "(" + room.getRoom_number() + "," + room.getCapacity() + "," + room.getId_hotel() + ");");
            jdbcConnection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRoom(Room room) {
        return 0;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public Room findRoom() {
        return null;
    }
}
