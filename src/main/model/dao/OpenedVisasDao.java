package main.model.dao;

import main.model.domain.OpenedVisas;

import java.util.List;

/**
 * Created by Максим on 05.12.2015.
 */
public interface OpenedVisasDao {

    int createVisas(OpenedVisas visas);

    int deleteVisa(OpenedVisas visas);

    List<OpenedVisas> getAll();

    OpenedVisas findRoom();
}
