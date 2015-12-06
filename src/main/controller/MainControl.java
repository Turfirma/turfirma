package main.controller;

import main.model.dao.HotelDao;
import main.model.dao.OrdersDao;
import main.model.dao.RoomDao;
import main.model.daoImpl.*;
import main.model.domain.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bo4ek on 05.12.2015.
 */
public class MainControl {

    //1.1
    public List<Country> getAllCountries() {
        return new CountryDaoImpl().getAll();
    }

    //1.2
    public List<City> getAllCities() {
        return new CityDaoImpl().getAll();
    }

    //2
    public List<Hotel> checkForHotelInCity(String cityName) {
        List<City> listAllCities = getAllCities();
        int id_city = 0;
        for (City city : listAllCities) {
            if (city.getCity_name().equals(cityName)) {
                id_city = city.getId_city();
            }
        }
        List<Hotel> listOfAll = new HotelDaoImpl().getAll();
        List<Hotel> listInCity = new ArrayList<>();
        for (Hotel hotel : listOfAll) {
            if (hotel.getId_city() == id_city) {
                listInCity.add(hotel);
            }
        }
        return listInCity;
    }

    //6
    public int howManyVisas() {

        return 0;
    }

    //9

    public ArrayList<String> roomLoading (String fromDate, String toDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<String> resultList = new ArrayList<>();

        try {
            Date startDate = new Date(simpleDateFormat.parse(fromDate).getTime());
            Date endDate = new Date(simpleDateFormat.parse(toDate).getTime());

            HashSet<RoomIdAndDays> rooms = new HashSet<>();
            RoomDao roomDao = new RoomDaoImpl();
            RoomIdAndDays roomIdAndDays;
            Room room;

            OrdersDao ordersDao = new OrdersDaoImpl();
            List<Order> orders = ordersDao.getAll();

            int counter = 0;
            DateCompare dateCompare = new DateCompare();

            for (Order order : orders ){

                int result = (int) dateCompare.dateGetDifferenceInDateOrders(order,startDate,endDate);

                if (result != -1){
                    room = roomDao.getRoomById(order.getId_room());
                    roomIdAndDays = new RoomIdAndDays();
                    roomIdAndDays.setId_hotel(room.getId_hotel());
                    roomIdAndDays.setId_room(room.getId_room());
                    roomIdAndDays.setLoadingDays(result);

                    if (rooms.contains(roomIdAndDays)) {
                        for (RoomIdAndDays roomId : rooms){
                            if (roomId.getId_room() == roomIdAndDays.getId_room()) roomId.setLoadingDays(roomId.getLoadingDays()+result);
                        }
                    }
                    else{
                        rooms.add(roomIdAndDays);
                    }
                    counter ++;
                }
            }

            if (counter == 0){
                resultList.add("No rooms is being booked" );
                return resultList;
            }
            System.out.println("Country|City|Hotel|Room|Loading Days");

            for (RoomIdAndDays roomIterator:rooms){
                String stringStatistic = roomDao.getCountrCityHotelbyId(roomIterator.getId_room())
                        + roomIterator.getLoadingDays();
                resultList.add(stringStatistic);
                System.out.println(stringStatistic);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resultList.add("The date is invalid");
        return resultList;
    }

    private class RoomIdAndDays extends Room {

         private int loadingDays;



        public void setLoadingDays(int loadingDays) {
            this.loadingDays = loadingDays;
        }

        public int getLoadingDays() {
            return loadingDays;
        }

        @Override
        public int hashCode(){
            return RoomIdAndDays.this.getId_room();
        }

        @Override
        public String toString() {
            return "RoomIdAndDays{" +
                    "id_room=" + super.getId_room() +
                    "loadingDays=" + loadingDays +
                    '}';
        }
    }

    //10
    public  ArrayList<String> hotelStatistic(){
        HotelDao hotelDao = new HotelDaoImpl();
        /*List<>*/
        return null;
    }


}
