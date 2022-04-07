package Services;

import Client.Client;
import Date.Date;
import Event.*;
import Location.*;
import Date.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public interface IMainService {

    Client createClient(Scanner in);
    void listClients();

    Location addLocation(Scanner in);
    SectionedLocation addSectionedLocation(Scanner in);
    void listLocations();
    void listLocationsByDescCapacity();

    Event createEvent(Scanner in);
    void listEvents();

    Festival createFestival(Scanner in);

    double sellAdultDayPassForCategory(Scanner in);

    double sellDiscountedDayPassForCategory(Scanner in);

    double sellAdultFullPassForCategory(Scanner in);

    void listClientTickets(Scanner in);
//
//    double sellLimitedPass(Event event, Client client, int category,
//                           int hours, Time start, int amount, int price);

    List<Event> getAllEventsStartingFrom(Scanner in);
    void listAllEventsStartingFrom(Scanner in);

    List<Event> getAllEventsUntil(Scanner in);
    void listAllEventsUntil(Scanner in);

}
