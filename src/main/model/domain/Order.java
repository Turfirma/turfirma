package main.model.domain;

import java.sql.Date;

/**
 * Created by Максим on 05.12.2015.
 */
public class Order {

    private int id_orders;
    private int id_hotel;
    private int id_room;
    private int id_client;

    private Date check_in;
    private Date check_out;

    public Order() {
    }

    public Order(int id_hotel, int id_room, int id_client, Date check_in, Date check_out) {
        this.id_hotel = id_hotel;
        this.id_room = id_room;
        this.id_client = id_client;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public Order(int id_orders, Date check_out, int id_client, int id_hotel, int id_room, Date check_in) {
        this.id_orders = id_orders;
        this.check_out = check_out;
        this.id_client = id_client;
        this.id_hotel = id_hotel;
        this.id_room = id_room;
        this.check_in = check_in;
    }

    public int getId_orders() {
        return id_orders;
    }

    public void setId_orders(int id_orders) {
        this.id_orders = id_orders;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id_orders=" + id_orders +
                ", id_hotel=" + id_hotel +
                ", id_room=" + id_room +
                ", id_client=" + id_client +
                ", check_in=" + check_in +
                ", check_out=" + check_out +
                '}';
    }
}
