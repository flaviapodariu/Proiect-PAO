import Entities.Location.*;
import Entities.Client.Client;
import Entities.Ticket.*;
import Entities.Event.*;
import Queries.ClientQueries;
import Queries.EventQueries;
import Queries.LocationQueries;
import Services.*;
import config.DatabaseConfiguration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class Main {

    private static final List<String> commands = new ArrayList<>();

    public static void main(String[] args) throws IOException, SQLException {
        MainService service = MainService.getInstance();
        Scanner in = new Scanner(System.in);
        boolean stop = false;

        initializeCommands();
        System.out.println("Available commands:");
        listCommands();

//        ETAPA 2 -> READ/ WRITE FROM CSV
        AuditService audit = new AuditService("data/audit.csv");
//        ClientCSVService clientCSV  = ClientCSVService.getInstance();
//        EventCSVService eventCSV = EventCSVService.getInstance();
//        LocationCSVService locationCSV = LocationCSVService.getInstance();
//        TicketCSVService ticketCSV = TicketCSVService.getInstance();
        WriteService out = WriteService.getInstance();
//
//
//        service.setClients(clientCSV.getFromCSV("data/client.csv"));
//        service.setLocations(locationCSV.getFromCSV("data/location.csv"));
//        service.setEvents(eventCSV.getFromCSV("data/event.csv"));
//        service.setTicketsSold(ticketCSV.getFromCSV("data/ticket.csv"));

//
//        while (!stop) {
//            System.out.println("Enter command: ");
//            String option = in.nextLine();
//
//            switch (option) {
//
//                case "add_client" -> {
//                    service.createClient(in);
//                    audit.logAction("add_client");
//                }
//                case "add_location" -> {
//                    service.addLocation(in);
//                    audit.logAction("add_location");
//                }
//                case "add_sectioned_loc" -> {
//                    service.addSectionedLocation(in);
//                    audit.logAction("add_sectioned_loc");
//                }
//                case "add_event" -> {
//                    service.createEvent(in);
//                    audit.logAction("add_event");
//                }
//                case "add_festival" -> {
//                    service.createFestival(in);
//                    audit.logAction( "add_festival");
//                }
//                case "get_clients" -> {
//                    List<Client> c = service.getClients();
//                    out.writeToFile("data/output.txt", c);
//                    audit.logAction("get_clients");
//                }
//                case "get_locations" -> {
//                    List<Location> l = service.getLocations();
//                    out.writeToFile("data/output.txt", l);
//                    audit.logAction("get_locations");
//                }
//                case "get_locations_by_desc_capacity" -> {
//                    service.listLocationsByDescCapacity();
//                    audit.logAction("get_locations_by_desc_capacity");
//                }
//                case "get_events" -> {
//                    List<Event> e = service.getEvents();
//                    out.writeToFile("data/output.txt", e);
//                    audit.logAction("get_events");
//                }
//                case "get_tickets" -> {
//                    List<Ticket> t = service.getTicketsSold();
//                    out.writeToFile("data/output.txt", t);
//                    audit.logAction("get_tickets");
//                }
//
//                case "get_tickets_of_clientID" -> {
//                    service.listClientTickets(in);
//                    audit.logAction("get_tickets_of_clientID");
//                }
//
//                case "get_events_from" -> {
//                    boolean ok = false;
//                    while(!ok) {
//                        try {
//                            service.listAllEventsStartingFrom(in);
//                            audit.logAction("get_events_from");
//                            ok = true;
//                        } catch (Exception e) {
//                            String log = "get_events_from ERROR " + e.getMessage();
//                            System.out.println("Please enter a date in dd-mm-yyyy format");
//                            audit.logAction(log);
//                        }
//                    }
//
//                }
//                case "get_events_until" -> {
//                    boolean ok = false;
//                    while(!ok) {
//                        try {
//                            service.listAllEventsUntil(in);
//                            audit.logAction("get_events_until");
//                            ok = true;
//                        } catch (Exception e) {
//                            String log = "get_events_until ERROR " + e.getMessage();
//                            System.out.println("Please enter a date in dd-mm-yyyy format");
//                            audit.logAction(log);
//                        }
//                    }
//                }
//                case "sell_full" -> {
//                    service.sellAdultFullPassForCategory(in);
//                    audit.logAction("sell_full");
//                }
//                case "sell_day" -> {
//                    service.sellAdultDayPassForCategory(in);
//                    audit.logAction("sell_day");
//                }
//                case "sell_day_discount" -> {
//                    service.sellDiscountedDayPassForCategory(in);
//                    audit.logAction("sell_day_discount");
//                }
//                case "-help" -> listCommands();
//                case "end" -> stop = true;
//                default -> System.out.println("Wrong command! Use -help for a list of full commands.");
//            }
//        }

        Connection conn = DatabaseConfiguration.getDatabaseInstance();
        ClientQueries clientQ = new ClientQueries(conn, service);
        LocationQueries locQ = new LocationQueries(conn, service);
        EventQueries evQ = new EventQueries(conn, service);

        clientQ.get();
        locQ.get();
        evQ.get();


        while (!stop) {
            System.out.println("Enter command: ");
            String option = in.nextLine();

            switch (option) {

                case "add_client" -> {
                    Client client = service.createClient(in);
                    clientQ.insert(client);
                    audit.logAction("add_client");
                }
                case "add_location" -> {
                    Location location = service.addLocation(in);
                    locQ.insert(location);
                    audit.logAction("add_location");
                }
                case "add_sectioned_loc" -> {
                    service.addSectionedLocation(in);
                    audit.logAction("add_sectioned_loc");
                }
                case "add_event" -> {
                    Event event = service.createEvent(in);
                    evQ.insert(event);
                    audit.logAction("add_event");
                }
                case "add_festival" -> {
                    service.createFestival(in);
                    audit.logAction("add_festival");
                }
                case "get_clients" -> {
                    List<Client> c = service.getClients();
                    clientQ.get();
                    service.listClients();
                    audit.logAction("get_clients");
                }
                case "get_locations" -> {
                    List<Location> l = service.getLocations();
                    locQ.get();
                    service.listLocations();
                    audit.logAction("get_locations");
                }
                case "get_locations_by_desc_capacity" -> {
                    service.listLocationsByDescCapacity();
                    audit.logAction("get_locations_by_desc_capacity");
                }
                case "get_events" -> {
                    List<Event> e = service.getEvents();
                    evQ.get();
                    service.listEvents();
                    audit.logAction("get_events");
                }
                case "get_tickets" -> {
                    List<Ticket> t = service.getTicketsSold();
                    out.writeToFile("data/output.txt", t);
                    audit.logAction("get_tickets");
                }

                case "get_tickets_of_clientID" -> {
                    service.listClientTickets(in);
                    audit.logAction("get_tickets_of_clientID");
                }

                case "delete_event_with_name" ->{
                    System.out.println("Enter event title");
                    String name = in.nextLine();
                    evQ.delete(name);
                }

                case "get_events_from" -> {
                    boolean ok = false;
                    while (!ok) {
                        try {
                            service.listAllEventsStartingFrom(in);
                            audit.logAction("get_events_from");
                            ok = true;
                        } catch (Exception e) {
                            String log = "get_events_from ERROR " + e.getMessage();
                            System.out.println("Please enter a date in dd-mm-yyyy format");
                            audit.logAction(log);
                        }
                    }

                }
                case "get_events_until" -> {
                    boolean ok = false;
                    while (!ok) {
                        try {
                            service.listAllEventsUntil(in);
                            audit.logAction("get_events_until");
                            ok = true;
                        } catch (Exception e) {
                            String log = "get_events_until ERROR " + e.getMessage();
                            System.out.println("Please enter a date in dd-mm-yyyy format");
                            audit.logAction(log);
                        }
                    }
                }
                case "sell_full" -> {
                    service.sellAdultFullPassForCategory(in);
                    audit.logAction("sell_full");
                }
                case "sell_day" -> {
                    service.sellAdultDayPassForCategory(in);
                    audit.logAction("sell_day");
                }
                case "sell_day_discount" -> {
                    service.sellDiscountedDayPassForCategory(in);
                    audit.logAction("sell_day_discount");
                }
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
        commands.add("get_tickets");
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

