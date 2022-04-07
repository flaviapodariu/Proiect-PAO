import Client.Client;
import Date.*;
import Date.Date;
import Event.*;
import Location.*;
import Services.*;
import Ticket.*;


import java.util.*;


public class Main {

    private static List<String> commands = new ArrayList<String>();

    public static void main(String[] args) {
        MainService service = MainService.getInstance();

        Scanner in = new Scanner(System.in);
        boolean stop = false;

        while (!stop) {
            System.out.println("Enter command: ");
            String option = in.nextLine();
            initializeCommands();
            switch (option) {
                case "add_client" -> service.createClient(in);
                case "add_location" -> service.addLocation(in);
                case "add_sectioned_loc" -> service.addSectionedLocation(in);
                case "add_event" -> service.createEvent(in);
                case "add_festival" -> service.createFestival(in);
                case "get_clients" -> service.listClients();
                case "get_locations" -> service.listLocations();
                case "get_locations_by_desc_capacity" -> service.listLocationsByDescCapacity();
                case "get_events" -> service.listEvents();

                case "get_tickets_of_clientID" -> service.listClientTickets(in);

                case "get_events_from" -> service.listAllEventsStartingFrom(in);
                case "get_events_until" -> service.listAllEventsUntil(in);

                case "sell_full" -> service.sellAdultFullPassForCategory(in);
                case "sell_day" -> service.sellAdultDayPassForCategory(in);
                case "sell_day_discount" -> service.sellDiscountedDayPassForCategory(in);
                case "-help" -> listCommands();
                case "end" -> stop = true;
                default -> System.out.println("Wrong command! Use -help for a list of full commands.");
            }
        }
    }

    private static void initializeCommands() {
        commands.add("add_client");
        commands.add("add_location");
        commands.add("add_sectioned_loc");
        commands.add("add_event");
        commands.add("add_festival");
        commands.add("get_clients");
        commands.add("get_locations");
        commands.add("get_locations_by_desc_capacity");
        commands.add("get_events");
        commands.add("get_tickets_of_clientID");
        commands.add("get_events_from");
        commands.add("get_events_until");
        commands.add("sell_full");
        commands.add("sell_day");
        commands.add("sell_day_discount");
        commands.add("end");
    }

    private static void listCommands() {
        for (String comm : commands) {
            System.out.println(comm);
        }


        //
//        Date dob = new Date(26, 3, 2002);
//        List<Ticket> t = new ArrayList<Ticket>();
//        Client c = new Client("F", "P", dob);
////        System.out.println(c);
//
//
//        // venue -> sectioned
//        HashMap<Integer, Integer> cc = new HashMap<Integer, Integer>();
//
//        SectionedLocation venue = new SectionedLocation("Romexpo", "Bucuresti", "Bd.Expozitiei",
//                                      3, 3, cc);
//
//        for(int i = 0; i < venue.getCategories(); i++)
//            venue.setCapacityForCategory(i, 2);
//
//        //location -> unsectioned
//
//        UnsectionedLocation location = new UnsectionedLocation("Parc Buftea", "Buftea", "str.Buftea nr.5",
//                                       75000);
//
//        //client
//       HashMap<String ,Integer> discounts = new HashMap<String, Integer>();
//       Event e =  service.createEvent("Iron Maiden", new Date(26, 5, 2022),
//                  venue, 18, new Time(18, 30), new Time(20, 0), discounts);
//
//       Festival f = service.createFestival("SummerWell", new Date(13, 8, 2022), location,
//                  16, new Time(18, 30), new Time(20, 0), new Date(15, 8, 2022));
//
//
//
//       List<Event> evs = service.getAllEventsStartingFrom(new Date(21, 3, 2022));
//       for(Event evv: evs){
//           System.out.println(evv + "\n");
//       }


//       System.out.println(Services.MainService.test(e));

//        double toPay = service.sellDayPassForCategory(e,c, 1,2, 200);
//        System.out.println(toPay);
//        System.out.println(c);
    }
}

