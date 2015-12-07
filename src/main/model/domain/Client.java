package main.model.domain;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class Client {
    private int id_client;
    private String countryName;
    private String first_name;
    private String last_name;
    private String email;

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public String getCountryName() {
        return countryName;
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

    public Client(String first_name, String last_name, String email, String countryName) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.countryName = countryName;
    }

    public Client(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Client() {
    }
}
