import Entities.Date.Date;
import Entities.Location.*;
import Entities.Client.Client;
import Entities.Ticket.*;
import Entities.Event.*;
import Queries.ClientQueries;
import Queries.DayPassQueries;
import Queries.EventQueries;
import Queries.LocationQueries;
import Services.*;
import config.DatabaseConfiguration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        DayPassQueries passQ = new DayPassQueries(conn, service);

//        LOADING DB
        clientQ.get();
        locQ.get();
        evQ.get();
        passQ.get();


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
                    clientQ.get();
                    service.listClients();
                    audit.logAction("get_clients");
                }
                case "get_locations" -> {
                    locQ.get();
                    service.listLocations();
                    audit.logAction("get_locations");
                }
                case "get_locations_by_desc_capacity" -> {
                    locQ.get();
                    service.listLocationsByDescCapacity();
                    audit.logAction("get_locations_by_desc_capacity");
                }
                case "get_events" -> {
                    evQ.get();
                    service.listEvents();
                    audit.logAction("get_events");
                }
                case "get_tickets" -> {
                    passQ.get();
                    service.listTicketsSold();
                    audit.logAction("get_tickets");
                }

                case "get_tickets_of_clientID" -> {
                    clientQ.get();
                    service.listClientTickets(in);
                    audit.logAction("get_tickets_of_clientID");
                }
                case "update_event_date" ->{
                    System.out.println("Enter event name:");
                    String name = in.nextLine();
                    System.out.println("Enter new event date:(dd-mm-yyyy)");
                    Date date = Date.parser(in.nextLine());
                    evQ.update_date(name, date);
                }

                case "update_location_capacity" -> {
                    System.out.println("Enter location ID:");
                    int loc_id = in.nextInt();
                    System.out.println("Enter new capacity:");
                    int new_cap = in.nextInt();
                    locQ.update_capacity(new_cap, loc_id);
                }

                case "update_client_first_name" ->{
                    System.out.println("Enter client ID:");
                    int cl_id = in.nextInt();
                    System.out.println("Enter new first name for client");
                    String fName = in.nextLine();
                    clientQ.update(fName, cl_id);
                }

                case "update_ticket_validity" ->{
                    System.out.println("Enter ticket ID");
                    int id = in.nextInt();
                    System.out.println("Enter new valid date:(dd-mm-yyyy)");
                    Date date = Date.parser(in.nextLine());
                    passQ.update(date, id);
                }

                case "delete_event_with_name" ->{
                    System.out.println("Enter event title");
                    String name = in.nextLine();
                    evQ.delete(name);
                }
                case "delete_client_by_id" ->{
                    System.out.println("Enter client ID to delete:");
                    int id = in.nextInt();
                    clientQ.delete(id);
                }
                case "delete_location_by_id" ->{
                    System.out.println("Enter location ID to delete:");
                    int id = in.nextInt();
                    locQ.delete(id);
                }
                case "delete_daypass_by_id" ->{
                    System.out.println("Enter ticket ID to delete:");
                    int id = in.nextInt();
                    passQ.delete(id);
                }

                case "get_events_from" -> {
                    evQ.get();
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
                    evQ.get();
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
//                case "sell_full" -> {
//                    service.sellAdultFullPassForCategory(in);
//                    audit.logAction("sell_full");
//                }
                case "sell_day" -> {
                    DayPass t = service.sellAdultDayPassForCategory(in);
                    passQ.insert(t);
                    audit.logAction("sell_day");
                }
//                case "sell_day_discount" -> {
//                    service.sellDiscountedDayPassForCategory(in);
//                    audit.logAction("sell_day_discount");
//                }
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
        commands.add("update_event_date");
        commands.add("update_location_capacity");
        commands.add("update_client_first_name");
        commands.add("update_ticket_validity");
        commands.add("delete_event_with_name");
        commands.add("delete_client_by_id");
        commands.add("delete_location_by_id");
        commands.add("delete_daypass_by_id");
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

