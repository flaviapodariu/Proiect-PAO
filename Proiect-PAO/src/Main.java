import Services.*;

import java.util.*;


public class Main {

    private static List<String> commands = new ArrayList<>();

    public static void main(String[] args) {
        MainService service = MainService.getInstance();

        Scanner in = new Scanner(System.in);
        boolean stop = false;

        initializeCommands();
        System.out.println("Available commands:");
        listCommands();

        while (!stop) {
            System.out.println("Enter command: ");
            String option = in.nextLine();

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

    }

}