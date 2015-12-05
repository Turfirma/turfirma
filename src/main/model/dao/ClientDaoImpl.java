package main.model.dao;

import main.model.domain.City;
import main.model.domain.Client;
import main.model.resources.JDBCConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class ClientDaoImpl implements ClientDao {
    @Override
    public int createClient(Client client) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO turfirma.client (first_name, last_name, email, id_country) VALUES " +
                    "('" + client.getFirst_name()
                    + "','" + client.getLast_name()
                    + "','" + client.getEmail()
                    + "','" + client.getId_country()
                    + "');");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteClient(Client client) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM turfirma.client WHERE first_name LIKE '"
                    + client.getFirst_name()
                    + "' AND last_name LIKE '"
                    + client.getLast_name()
                    + "' AND email LIKE '"
                    + client.getEmail()
                    + "';");
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Client> getAll() {
        try {
            List<Client> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.client");
            while (result.next()) {
                Client client = new Client();
                client.setId_client(result.getInt(1));
                client.setFirst_name(result.getString(2));
                client.setLast_name(result.getString(3));
                client.setEmail(result.getString(4));
                client.setId_country(result.getInt(5));
                list.add(client);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
