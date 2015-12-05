package main.model.domain;

import java.sql.Date;

/**
 * Created by Максим on 05.12.2015.
 */
public class OpenedVisas {

    private int id_opened_visas;
    private int id_country;
    private int id_client;

    private Date start_date;
    private Date end_date;

    public OpenedVisas() {
    }

    public OpenedVisas(int id_country, int id_client, Date start_date, Date end_date) {
        this.id_country = id_country;
        this.id_client = id_client;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public OpenedVisas(int id_opened_visas, Date end_date, Date start_date, int id_client, int id_country) {
        this.id_opened_visas = id_opened_visas;
        this.end_date = end_date;
        this.start_date = start_date;
        this.id_client = id_client;
        this.id_country = id_country;
    }

    public int getId_opened_visas() {
        return id_opened_visas;
    }

    public void setId_opened_visas(int id_opened_visas) {
        this.id_opened_visas = id_opened_visas;
    }

    public int getId_country() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "OpenedVisas{" +
                "id_opened_visas=" + id_opened_visas +
                ", id_country=" + id_country +
                ", id_client=" + id_client +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
