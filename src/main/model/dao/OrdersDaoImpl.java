package main.model.dao;

import main.model.domain.Order;
import main.model.resources.JDBCConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public class OrdersDaoImpl implements OrdersDao {


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
        try{
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("delete from turfirma.orders where " +
                    "id_hotel = " + order.getId_hotel() +
                    " and id_room = " + order.getId_room() +
                    " and check_in = " + order.getCheck_in() +
                    " and check_out = " + order.getCheck_out() +
                    " and id_client = " + order.getId_client() +" ;");
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
                order.setId_orders(result.getInt(1));
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
    public Order findRoom() {
        return null;
    }
}
