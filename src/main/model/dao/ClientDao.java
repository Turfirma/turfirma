package main.model.dao;

import main.model.domain.Client;
import main.model.domain.Country;

import java.sql.Date;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public interface ClientDao {
    int createClient(Client client);

    int deleteClient(Client client);

    List<Client> findAll();

    int clientVisasAmount(Client client);

    int findIdClientInDB(Client client);

    List<String> currentVisas(Client client);

    List<String> visitedCountries(Client client);

    int checkClientVisas(int clientId, int countryId, Date check_in, Date check_out);
}
