package main.model.dao;

import main.model.domain.Client;

import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public interface ClientDao {
    int createClient(Client client);

    int deleteClient(Client client);

    List<Client> getAll();
}
