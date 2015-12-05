package main.model.domain;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class Client {
    private int id_client;
    private int id_country;
    private String first_name;
    private String last_name;
    private String email;

    public Client() {
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_client() {

        return id_client;
    }

    public int getId_country() {
        return id_country;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public Client(int id_country, String first_name, String last_name, String email) {

        this.id_country = id_country;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
