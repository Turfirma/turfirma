package main.model.daoImpl;

import main.model.dao.OpenedVisasDao;
import main.model.domain.Country;
import main.model.domain.OpenedVisas;
import main.model.resources.JDBCConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public class OpenedVisasDaoImpl implements OpenedVisasDao {

    @Override
    public int createVisas(OpenedVisas visas) {
        try {
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("insert into turfirma.opened_visas (id_country,start_date,end_date,id_client) " +
                    "values (" +
                    visas.getId_country() + " ,'" +
                    visas.getStart_date() + " ',' " +
                    visas.getEnd_date() + " ', " +
                    visas.getId_client() + ");");
            jdbcConnection.getConnection().close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteVisa(OpenedVisas visas) {
        try{
            JDBCConnection jdbcConnection = new JDBCConnection();
            Statement statement = jdbcConnection.getConnection().createStatement();
            int result = statement.executeUpdate("delete from turfirma.opened_visas where " +
                    "id_country = " + visas.getId_country() +
                    " and start_date = ' " + visas.getStart_date() + " ' " +
                    " and end_date = ' " + visas.getEnd_date() + " ' " +
                    " and id_client = " + visas.getId_client() +" ;");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OpenedVisas> getAll() {
        try {
            List<OpenedVisas> list = new ArrayList<>();
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM turfirma.opened_visas");
            while (result.next()) {
                OpenedVisas openedVisas = new OpenedVisas();
                openedVisas.setId_opened_visas(result.getInt(1));
                openedVisas.setId_country(result.getInt(2));
                openedVisas.setStart_date(result.getDate(3));
                openedVisas.setEnd_date(result.getDate(4));
                openedVisas.setId_client(result.getInt(5));
                list.add(openedVisas);
            }
            connection.getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OpenedVisas findRoom() {
        return null;
    }

    public int howManyVisas(Country country) {
        try {
            JDBCConnection connection = new JDBCConnection();
            Statement statement = connection.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(id_country) FROM turfirma.opened_visas " +
                    "WHERE id_country=(SELECT id_country FROM turfirma.country WHERE country_name " +
                    "LIKE '" + country.getCountry_name() + "');");
            set.next();
            return set.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
