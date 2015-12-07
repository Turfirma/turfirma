package main.model.daoImpl;

import main.model.dao.ClientDao;
import main.model.domain.Client;
import main.model.domain.Country;
import main.model.resources.JDBCConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: turfirma
 *
 * Created by bo4ek
 * Date: 05.12.2015
 */
public class ClientDaoImpl implements ClientDao {
    private static final String CLIENT_VISAS_AMOUNT = "SELECT COUNT(id_client) FROM turfirma.opened_visas WHERE id_client = " +
            "(SELECT id_client FROM client WHERE client.last_name LIKE ? " +
            "AND client.first_name LIKE ? AND client.email LIKE ?);";

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

    private static final String CHECK_CLIENT_VISA = "SELECT COUNT(opened_visas.id_country) > 0 FROM " +
            "opened_visas WHERE (start_date <= ? AND end_date >= ? AND id_country = ? AND id_client = ?);";

    private static final String INSERT_CLIENT = "INSERT INTO client (first_name, last_name, email, id_country) " +
            "VALUES (?,?,?,(SELECT id_country FROM country WHERE country_name LIKE ?));";

    private static final String DELETE_CLIENT = "DELETE FROM client WHERE first_name LIKE ? " +
            "AND last_name LIKE ? AND email LIKE ?;";

    private JDBCConnection connection;

    @Override
    public int createClient(Client client) {
        try {
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(INSERT_CLIENT);
            ps.setString(1, client.getFirst_name());
            ps.setString(2, client.getLast_name());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getCountryName());
            int result = ps.executeUpdate();
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
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(DELETE_CLIENT);
            ps.setString(1, client.getFirst_name());
            ps.setString(2, client.getLast_name());
            ps.setString(3, client.getEmail());
            int result = ps.executeUpdate();
            connection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Client> findAll() {
        try {
            List<Client> list = new ArrayList<>();
            connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM turfirma.client");
            while (set.next()) {
                Client client = new Client();
                client.setId_client(set.getInt(1));
                client.setFirst_name(set.getString(2));
                client.setLast_name(set.getString(3));
                client.setEmail(set.getString(4));
                client.setCountryName(set.getString(5));
                list.add(client);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int clientVisasAmount(Client client) {
        try {
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(CLIENT_VISAS_AMOUNT);
            ps.setString(1, client.getLast_name());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getEmail());
            ResultSet set = ps.executeQuery();
            set.next();
            int i = set.getInt(1);
            connection.getConnection().close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int findIdClientInDB(Client client) {
        try {
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(ID_CLIENT);
            ps.setString(1, client.getLast_name());
            ps.setString(2, client.getFirst_name());
            ps.setString(3, client.getEmail());
            ResultSet set = ps.executeQuery();
            set.next();
            int i = set.getInt(1);
            connection.getConnection().close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> currentVisas(Client client) {
        try {
            List<String> list = new ArrayList<>();
            int id_client = findIdClientInDB(client);
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(CURRENT_VISAS);
            ps.setInt(1, id_client);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> visitedCountries(Client client) {
        try {
            List<String> list = new ArrayList<>();
            int id_client = findIdClientInDB(client);
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(VISITED_COUNTRIES);
            ps.setInt(1, id_client);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int checkClientVisas(int clientId, int idDestinationCountry, Date check_in, Date check_out) {
        try {
            connection = new JDBCConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(CHECK_CLIENT_VISA);
            ps.setDate(1, check_in);
            ps.setDate(2, check_out);
            ps.setInt(3, idDestinationCountry);
            ps.setInt(4, clientId);
            ResultSet set = ps.executeQuery();
            set.next();
            int i = set.getInt(1);
            connection.getConnection().close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
