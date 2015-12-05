package main.model.dao;

import main.model.domain.Order;

import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public interface OrdersDao {


    int createOrder(Order order);

    int deleteOrder(Order order);

    List<Order> getAll();

    Order findRoom();
}
