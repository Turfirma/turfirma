package main.model.dao;

import main.model.domain.Hotel;
import main.model.domain.Order;
import main.model.domain.Room;

import java.sql.Date;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public interface OrdersDao {

    int createOrder(Order order);

    int deleteOrder(Order order);

    List<Order> getAll();

    List<Room> findFreeRooms(int hotelId, Date checkIn, Date checkOut);

    List<Hotel> findFreeHotels(int cityId, Date checkIn, Date checkOut);
}
