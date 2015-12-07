package main.model.daoImpl;

import main.model.dao.OrdersDao;
import main.model.domain.Hotel;
import main.model.domain.Order;
import main.model.domain.Room;
import main.model.resources.JDBCConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: turfirma
 *
 * Created by Максим
 * Date: 05.12.2015
 */
public class OrdersDaoImpl implements OrdersDao {

    private static final String AVAILABLE_ROOMS = "SELECT room.room_number, room.capacity, room.id_room " +
            "FROM room WHERE room.id_hotel = ? AND room.id_room NOT IN " +
            "(SELECT orders.id_room FROM orders WHERE " +
            "(orders.check_out >= ? AND orders.check_in <=  ?) " +
            "OR (orders.check_out <= ? AND orders.check_in >=  ?));";

    @Override
    public int createOrder(Order order) {
        try {
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("insert into turfirma.orders (id_hotel,id_room,check_in,check_out,id_client) \n" +
                    "values (" +
                    order.getId_hotel() + " , " +
                    order.getId_room() + " ,'" +
                    order.getCheck_in() + " ',' " +
                    order.getCheck_out() + " ', " +
                    order.getId_client() + ");");
            jdbcConnection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrder(Order order) {
        try {
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("delete from turfirma.orders where " +
                    "id_hotel = " + order.getId_hotel() +
                    " and id_room = " + order.getId_room() +
                    " and check_in = ' " + order.getCheck_in() + " ' " +
                    " and check_out = ' " + order.getCheck_out() + " ' " +
                    " and id_client = " + order.getId_client() + " ;");
            jdbcConnection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> getAll() {
        try {
            List<Order> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.orders");
            while (result.next()) {
                Order order = new Order();
                order.setId_order(result.getInt(1));
                order.setId_hotel(result.getInt(2));
                order.setId_room(result.getInt(3));
                order.setCheck_in(result.getDate(4));
                order.setCheck_out(result.getDate(5));
                order.setId_client(result.getInt(6));
                list.add(order);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> findFreeRooms(int hotel_id, Date check_in, Date check_out) {
        try {
            List<Room> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            System.out.println("Checking for available rooms before Prepared");
            PreparedStatement ps = connection.getConnection().prepareStatement(AVAILABLE_ROOMS);
            System.out.println("Checking for available rooms after Prepared");
            ps.setInt(1, hotel_id);
            ps.setDate(2, check_in);
            ps.setDate(3, check_out);
            ps.setDate(4, check_in);
            ps.setDate(5, check_out);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId_hotel(hotel_id);
                room.setRoom_number(resultSet.getInt(1));
                room.setCapacity(resultSet.getInt(2));
                room.setId_room(resultSet.getInt(3));
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
    public List<Hotel> findFreeHotels(int cityId, Date checkIn, Date checkOut) {
        List<Hotel> allHotels = new HotelDaoImpl().findHotelByCityId(cityId);
        List<Hotel> availableHotels = new ArrayList<>();
        for (Hotel hotel: allHotels) {
            if (findFreeRooms(hotel.getId_hotel(),checkIn,checkOut).size()>=1) {
                availableHotels.add(hotel);
            }
        }
        return availableHotels;
    }
}
