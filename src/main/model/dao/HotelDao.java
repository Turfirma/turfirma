package main.model.dao;

import main.model.domain.Hotel;

import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public interface HotelDao {
    int createHotel(Hotel hotel);
    int deleteHotel(Hotel hotel);
    List<Hotel> getAll();
}
