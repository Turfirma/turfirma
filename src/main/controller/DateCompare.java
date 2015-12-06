package main.controller;

import main.model.domain.Order;

import java.sql.Date;

/**
 * Class is used to for comparing date in different object.
 */
public class DateCompare {

    public long dateGetDifferenceInDateOrders(Order order,Date startDate, Date endDate){
        if (order.getCheck_out().compareTo(startDate) >= 0 && order.getCheck_in().compareTo(startDate)<0){
            return  (order.getCheck_out().getTime() - startDate.getTime())/(24*60*60*1000);
        }
        if (order.getCheck_in().compareTo(startDate)>=0 && order.getCheck_out().compareTo(endDate)<=0){
            return (order.getCheck_out().getTime()-order.getCheck_in().getTime())/(24*60*60*1000);
        }
        if (order.getCheck_in().compareTo(endDate)<=0 && order.getCheck_out().compareTo(endDate)>0){
            return (endDate.getTime()-order.getCheck_out().getTime())/(24*60*60*1000);
        }
        return -1;
    }
}
