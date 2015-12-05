package main;

import main.model.dao.CountryDao;
import main.model.dao.CountryDaoImpl;
import main.model.dao.RoomDao;
import main.model.dao.RoomDaoImpl;
import main.model.domain.Country;
import main.model.domain.Room;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) {
        Room room = new Room();
        room.setId_hotel(4);
        room.setCapacity(4);
        room.setRoom_number(4);
        RoomDao roomDao = new RoomDaoImpl();
        roomDao.createRoom(room);
    }
}
