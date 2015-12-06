package main;


import main.controller.MainControl;
import main.model.dao.OpenedVisasDao;
import main.model.daoImpl.OpenedVisasDaoImpl;
import main.model.domain.OpenedVisas;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) throws ParseException {


        MainControl mainControl = new MainControl();
        mainControl.roomLoading("2015-01-01","2016-01-01");




        /*OrdersDao ordersDao = new OrdersDaoImpl();
        Order order = new Order();
        order.setId_client(7);
        order.setId_room(7);
        order.setId_hotel(7);
        String startDate = "2018-07-28";
        String endDate = "2018-08-28";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        order.setCheck_in(new Date(simpleDateFormat.parse(startDate).getTime()));
        order.setCheck_out(new Date(simpleDateFormat.parse(endDate).getTime()));
        System.out.println(order);

        System.out.println(ordersDao.createOrder(order));

        System.out.println(ordersDao.deleteOrder(order));*/

       /* OpenedVisasDao openedVisasDao = new OpenedVisasDaoImpl();


       List<OpenedVisas> orders = openedVisasDao.getAll();
        for (OpenedVisas order:orders){
            System.out.println(order);
        }*/

    }
}
