package main;

import main.controller.MainControl;
import main.model.daoImpl.OpenedVisasDaoImpl;
import main.model.domain.Country;
import main.model.domain.Hotel;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Максим on 04.12.2015.
 */

public class Test {

    public static void main(String[]args) throws ParseException {

        MainControl mainControl = new MainControl();
        List<Hotel> list = mainControl.checkForHotelInCity("Lviv");
        for (Hotel h: list) {
            System.out.println(h.getHotel_name());
        }

        OpenedVisasDaoImpl openedVisasDao = new OpenedVisasDaoImpl();
        Country country = new Country("USA");
        System.out.println(openedVisasDao.howManyVisas(country));

    }
}
