package main.model.daoImpl;

import main.model.dao.ClientDao;
import main.model.domain.Client;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class ClientDaoImpl implements ClientDao {
    private static final String TASK5 = "SELECT COUNT(id_client) FROM turfirma.opened_visas WHERE id_client = ";

    private static final String ID_CLIENT = "(SELECT id_client FROM client WHERE client.last_name LIKE ? " +
            "AND client.first_name LIKE ? AND client.email LIKE ?);";

    private static final String CURRENT_VISAS = "SELECT country.country_name FROM country " +
            "JOIN opened_visas ON country.id_country = opened_visas.id_country " +
            "JOIN client ON opened_visas.id_client = client.id_client " +
            "WHERE client.id_client = ? AND end_date >= CURRENT_DATE();";

    private static final String VISITED_COUNTRIES = "SELECT country.country_name FROM country " +
            "JOIN hotels ON country.id_country = hotels.id_country " +
            "JOIN orders ON hotels.id_hotel = orders.id_hotel " +
            "WHERE orders.id_client = ? " +
            "AND CURDATE() >= orders.check_in;";

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

    public int clientVisasAmount(Client client) {
        try {
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(TASK5 + ID_CLIENT);
            ps.setString(1, client.getLast_name());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getEmail());
            ResultSet set = ps.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIdClientFromDB(Client client) {
        try {
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(ID_CLIENT);
            ps.setString(1, client.getLast_name());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getEmail());
            ResultSet set = ps.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> currentVisas(Client client) {
        try {
            List<String> list = new ArrayList<>();
            int id_client = getIdClientFromDB(client);
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(CURRENT_VISAS);
            ps.setInt(1, id_client);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> visitedCountries(Client client) {
        try {
            List<String> list = new ArrayList<>();
            int id_client = getIdClientFromDB(client);
            JDBCConnection connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(VISITED_COUNTRIES);
            ps.setInt(1, id_client);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
