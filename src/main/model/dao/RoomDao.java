package main.model.dao;

import main.model.domain.Room;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public interface RoomDao {

    int createRoom(Room room);

    int deleteRoom(Room room);

    List<Room> getAll();

    Room findRoom();
}
