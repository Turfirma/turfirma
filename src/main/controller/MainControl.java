package main.controller;

import main.model.dao.HotelDao;
import main.model.dao.OrdersDao;
import main.model.dao.RoomDao;
import main.model.daoImpl.*;
import main.model.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Project name: turfirma
 *
 * Created by bo4ek
 * Date: 05.12.2015
 */
public class MainControl {

    //1.1.
    public List<Country> findAllCountries() {
        return new CountryDaoImpl().getAll();
    }

    //1.2.
    public List<City> findAllCities() {
        return new CityDaoImpl().getAll();
    }

    //2.
    public List<Hotel> checkForHotelInCity(String cityName) {
        List<City> listAllCities = findAllCities();
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

    //3.
    public List<Room> checkForAvailableRooms(int hotelId, Date check_in, Date check_out) {
        return new OrdersDaoImpl().findFreeRooms(hotelId, check_in, check_out);
    }

    //4.
    public List<Hotel> checkForAvailableHotels(int cityId, Date check_in, Date check_out) {
        return new OrdersDaoImpl().findFreeHotels(cityId, check_in, check_out);
    }

    //5.
    public int quantityOfClientsVisas(String firstName, String lastName, String email) {
        return new ClientDaoImpl().clientVisasAmount(new Client(firstName, lastName, email));
    }

    //6.
    public int howManyVisas(String country) {
        return new OpenedVisasDaoImpl().howManyVisas(country);
    }

    //7. Чи можна забронювати для конкретного клієнта
    public boolean checkClientValidation(int clientId, int countryId, Date check_in, Date check_out) {
        return new ClientDaoImpl().checkClientVisas(clientId, countryId, check_in, check_out) > 0;
    }

    //8.
    public String clientStatistics(String firstName, String lastName, String email) {
        List<String> listOfCurrentVisas;
        List<String> listOfVisitedCountries;
        ClientDaoImpl cd = new ClientDaoImpl();
        listOfCurrentVisas = cd.currentVisas(new Client(firstName, lastName, email));
        listOfVisitedCountries = cd.visitedCountries(new Client(firstName, lastName, email));
        System.out.println("Current");
        for (String s : listOfCurrentVisas) {
            System.out.println(s + " ");
        }
        System.out.println("Visited");
        for (String s : listOfVisitedCountries) {
            System.out.println(s + " ");
        }
        return null;
    }

    //9.
    public ArrayList<String> roomLoading(String fromDate, String toDate) {

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

            for (Order order : orders) {

                int result = (int) dateCompare.dateGetDifferenceInDateOrders(order, startDate, endDate);

                if (result != -1) {
                    room = roomDao.getRoomById(order.getId_room());
                    roomIdAndDays = new RoomIdAndDays();
                    roomIdAndDays.setId_hotel(room.getId_hotel());
                    roomIdAndDays.setId_room(room.getId_room());
                    roomIdAndDays.setLoadingDays(result);

                    if (rooms.contains(roomIdAndDays)) {
                        for (RoomIdAndDays roomId : rooms) {
                            if (roomId.getId_room() == roomIdAndDays.getId_room())
                                roomId.setLoadingDays(roomId.getLoadingDays() + result);
                        }
                    } else rooms.add(roomIdAndDays);
                    counter++;
                }
            }

            if (counter == 0) {
                resultList.add("No rooms is being booked");
                return resultList;
            }
            System.out.println("Country|City|Hotel|Room|Loading Days");

            String stringStatistic;

            for (RoomIdAndDays roomIterator : rooms) {
                stringStatistic = roomDao.getCountrCityHotelbyId(roomIterator.getId_room())
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

    //10.
    public ArrayList<String> hotelStatistic() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> resultList = new ArrayList<>();

        HashSet<HotelsAndDays> hotelsAndDayses = new HashSet<>();
        HotelDao hotelDao = new HotelDaoImpl();
        List<Hotel> hotelList = hotelDao.getAll();

        for (Hotel hotel : hotelList) {
            HotelsAndDays hotelsAndDays = new HotelsAndDays(hotel.getId_hotel(), hotel.getHotel_name());
            hotelsAndDayses.add(hotelsAndDays);
        }

        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Order> orderList = ordersDao.getAll();

        if (orderList.isEmpty()) {
            String end = "No orders i DATABASE";
            resultList.add(end);
            return resultList;
        }

        int orderDays;
        for (Order order : orderList) {
            orderDays = (int) (order.getCheck_out().getTime() - order.getCheck_in().getTime()) / (24 * 60 * 60 * 1000);
            for (HotelsAndDays hotelsAndDays : hotelsAndDayses) {
                if (hotelsAndDays.getId_hotel() == order.getId_hotel()) {
                    hotelsAndDays.setLoadingDays(hotelsAndDays.getLoadingDays() + orderDays);
                    hotelsAndDays.setClients(hotelsAndDays.getClients() + 1);
                }
            }
        }

        System.out.println("Country|City|Hotel|Clients|Average mount of days");

        String statistics;

        for (HotelsAndDays hotelsAndDays : hotelsAndDayses) {
            if (hotelsAndDays.getClients() != 0)
                hotelsAndDays.setAvarageOrder((float) hotelsAndDays.getLoadingDays() / hotelsAndDays.getClients());
            statistics = hotelDao.getCountryCityById(hotelsAndDays.getId_hotel()) + " | "
                    + hotelsAndDays.getHotel_name() + " | "
                    + hotelsAndDays.getClients() + " | " + hotelsAndDays.getAvarageOrder();
            resultList.add(statistics);
            System.out.println(statistics);
        }
        return resultList;
    }

    private class HotelsAndDays extends Hotel {

        private int loadingDays;
        private int clients;
        private float avarageOrder;

        HotelsAndDays(int iD, String hotel_name) {
            super.setHotel_name(hotel_name);
            this.setId_hotel(iD);
        }

        public int getLoadingDays() {
            return loadingDays;
        }

        public void setLoadingDays(int loadingDays) {
            this.loadingDays = loadingDays;
        }

        public int getClients() {
            return clients;
        }

        public void setClients(int clients) {
            this.clients = clients;
        }

        public double getAvarageOrder() {
            return avarageOrder;
        }

        public void setAvarageOrder(float avarageOrder) {
            this.avarageOrder = avarageOrder;
        }
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
        public int hashCode() {
            return RoomIdAndDays.this.getId_room();
        }
    }
}
