package main.model.dao;

import main.model.domain.OpenedVisas;
import main.model.resources.JDBCConnection;

import java.sql.Statement;
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
            int result = statement.executeUpdate("insert into turfirma.opened_visa (id_country,start_date,end_date,id_client) " +
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
            int result = statement.executeUpdate("delete from turfirma.opened_visa where " +
                    "id_country = " + visas.getId_country() +
                    " and start_date = " + visas.getStart_date() +
                    " and end_date = " + visas.getEnd_date() +
                    " and id_client = " + visas.getId_client() +" ;");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OpenedVisas> getAll() {
        return null;
    }

    @Override
    public OpenedVisas findRoom() {
        return null;
    }
}
