package Services;
import Entities.Location.*;
import Entities.Event.*;
import Entities.Client.*;
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
    void listTicketsSold();

    Festival createFestival(Scanner in);

    double sellAdultDayPassForCategory(Scanner in);

    double sellDiscountedDayPassForCategory(Scanner in);

    double sellAdultFullPassForCategory(Scanner in);

    void listClientTickets(Scanner in);

    List<Event> getAllEventsStartingFrom(Scanner in);
    void listAllEventsStartingFrom(Scanner in);

    List<Event> getAllEventsUntil(Scanner in);
    void listAllEventsUntil(Scanner in);

}
