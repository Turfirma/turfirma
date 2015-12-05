package main;


import main.model.dao.OpenedVisasDao;
import main.model.dao.OpenedVisasDaoImpl;
import main.model.domain.OpenedVisas;
import main.model.domain.Order;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) throws ParseException {

        OpenedVisasDao openedVisasDao = new OpenedVisasDaoImpl();
        OpenedVisas openedVisas = new OpenedVisas();
        String startDate = "2018-07-28";
        String endDate = "2018-08-28";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        openedVisas.setId_client(8);
        openedVisas.setId_country(8);
        openedVisas.setStart_date(new Date(simpleDateFormat.parse(startDate).getTime()));
        openedVisas.setEnd_date(new Date(simpleDateFormat.parse(endDate).getTime()));
        System.out.println(openedVisas);
        System.out.println(openedVisasDao.deleteVisa(openedVisas));
        /*ordersDao.deleteOrder(order);
        List<Order> orders = ordersDao.getAll();
        for (Order order:orders){
            System.out.println(order);
        }*/

    }
}
