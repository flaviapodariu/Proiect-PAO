package Services;
import Comparators.CapacityComparator;
import Entities.Date.Date;
import Entities.Location.*;
import Entities.Event.*;
import Entities.Client.*;
import Entities.Ticket.*;
import java.time.LocalDate;
import java.util.*;

public class MainService implements IMainService {

    private List<Client> clients;
    private List<Location> locations;
    private List<Event> events;
    private List<Ticket> ticketsSold;
    private HashMap<Client, List<Ticket>> ticketRegistry;

    private static MainService instance = null;

    private MainService() {
        this.clients =  new ArrayList<>();
        this.locations = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public static MainService getInstance() {
        if (instance == null)
            instance = new MainService();
        return instance;
    }

    // GETTERS / SETTERS
    public List<Client> getClients() {
        return clients;
    }

    public void listClients() {
        for (Client c : this.clients) {
            System.out.println(c + "\n");
        }
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public HashMap<Client, List<Ticket>> getTicketRegistry() {
        return ticketRegistry;
    }

    public void listLocations() {
        for (Location l : this.locations) {
            System.out.println(l + "\n");
        }
    }

    @Override
    public void listLocationsByDescCapacity(){
        CapacityComparator comp = new CapacityComparator();
        this.locations.sort(comp);
        for(Location l: this.locations){
            System.out.println(l);
        }
    }


    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void listEvents() {
        for (Event e : this.events) {
            System.out.println(e + "\n");
        }
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void listTicketsSold(){
        for(Ticket t: this.ticketsSold){
            System.out.println(t + "\n");
        }
    }


    private void mapTicketToClient(Ticket ticket, Client client){
        List<Ticket> tickets;
        if(this.ticketRegistry.containsKey(client))
            tickets = this.ticketRegistry.get(client);

        else tickets = new ArrayList<>();

        tickets.add(ticket);
        this.ticketRegistry.put(client, tickets);
    }

    public void setTicketsSold(List<Ticket> ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    // INTERFACE IMPLEMENTATION
    // CREATE/ADD
    @Override
    public Client createClient(Scanner in) {
        Client c = new Client(in);
        clients.add(c);
        return c;
    }

    @Override
    public SectionedLocation addSectionedLocation(Scanner in) {
        SectionedLocation l = new SectionedLocation(in);

        l.setCapacityForCategory(3, 1);
        l.setCapacityForCategory(2, 1);
        l.setCapacityForCategory(1, 1);

        locations.add(l);
        return l;
    }

    //
    @Override
    public Location addLocation(Scanner in) {
        Location l = new Location(in);
        locations.add(l);
        return l;
    }

    @Override
    public Event createEvent(Scanner in) {
        Location l = this.readLocationForEvent();
        Event e = new Event(in, l);
        this.events.add(e);
        return e;
    }

    @Override
    public Festival createFestival(Scanner in) {
        Location l = this.readLocationForEvent();
        return new Festival(in, l);
    }

    @Override
    public double sellAdultDayPassForCategory(Scanner in) {
        Event event = this.eventExistsHelper(in);
        Client client = this.clientExistsHelper(in);
        if(event == null || client == null)
            return 0;

        int minAge = event.getMinAge();
        if (!client.validateAge(event.getDate(), minAge)) {
            System.out.println("The client is not old enough!");
            return 0;
        }

        SectionedLocation venue = (SectionedLocation)event.getVenue();
        System.out.println("Enter the wanted category:");
        int category = Integer.parseInt(in.nextLine());
        if(venue.isAvailable(category, 1)){
            Ticket pass = new DayPass(in, category, event, false);
            client.addTicket(pass);
            this.mapTicketToClient(pass, client);
            return pass.getFullPrice();
        }
        return 0;

    }

    @Override
    public double sellDiscountedDayPassForCategory(Scanner in){
        Event event = this.eventExistsHelper(in);
        Client client = this.clientExistsHelper(in);
        if(event == null || client == null)
            return 0;

        int minAge = event.getMinAge();
        if (!client.validateAge(event.getDate(), minAge)) {
            System.out.println("The client is not old enough!");
            return 0;
        }

        String cat = this.chooseDiscountCategory(in, event);
        if(cat.equals("")){
            System.out.println("Try buying a full priced ticket or change the category");
            return 0;
        }

        SectionedLocation venue = (SectionedLocation)event.getVenue();
        System.out.println("Enter the wanted category:");
        int category = Integer.parseInt(in.nextLine());
        if(venue.isAvailable(category, 1)){
            Ticket pass = new DayPass(in, category, event, true);
            client.addTicket(pass);
            this.mapTicketToClient(pass, client);
            return pass.getPriceWithDiscount(event.getDiscount(cat));
        }
        return 0;

    }


    @Override
    public double sellAdultFullPassForCategory(Scanner in){
        Event event = this.eventExistsHelper(in);
        Client client = this.clientExistsHelper(in);
        if(event == null || client == null)
            return 0;

        int minAge = event.getMinAge();
        if (!client.validateAge(event.getDate(), minAge)) {
            System.out.println("The client is not old enough!");
            return 0;
        }
        SectionedLocation venue = (SectionedLocation)event.getVenue();
        System.out.println("Enter the wanted category:");
        int category = Integer.parseInt(in.nextLine());
        if(venue.isAvailable(category, 1)){
            Ticket pass = new FullPass(in, category, event, false);
            client.addTicket(pass);
            this.mapTicketToClient(pass, client);
            return pass.getFullPrice();
        }
        return 0;
    }
    @Override
    public void listClientTickets(Scanner in){

        System.out.println("Client ID: ");
        Client client = this.getClientByID(in.nextInt());
        if(this.ticketRegistry.containsKey(client)){
            List<Ticket> tickets = this.ticketRegistry.get(client);
            System.out.println("The client's tickets are:\n ");
            for(Ticket t: tickets){
                System.out.println(t);
            }
        }
        else System.out.println("This client doesn't have any tickets!");

    }

    // QUERIES
    @Override
    public List<Event> getAllEventsStartingFrom(Scanner in){
        List<Event> eventList = new ArrayList<Event>();
        System.out.println("Enter start date: ");
        Date start = Date.parser(in.nextLine());

        for(Event e: this.events){
           if(this.isFestival(e))
           {
               Festival f = (Festival)e;
               if(!start.isGreater(f.getEndDate()))
                   eventList.add((Event)f);
           }
           else if(e.getDate().isGreaterOrEqual(start)){
                eventList.add(e);
            }
        }
        return eventList;
    }
    public void listAllEventsStartingFrom(Scanner in){
        List<Event> eventList = this.getAllEventsStartingFrom(in);
        for(Event e: eventList){
            System.out.println(e);
        }
    }

    @Override
    public List<Event> getAllEventsUntil(Scanner in) {
        List<Event> eventList = new ArrayList<>();
        System.out.println("Enter end date: ");
        Date end = Date.parser(in.nextLine());

        for (Event e : this.events) {
            Date eventDate = e.getDate();
            int d = LocalDate.now().getDayOfMonth();
            int m = LocalDate.now().getMonthValue();
            int y = LocalDate.now().getYear();
            Date today = new Date(d, m, y);

            if (eventDate.isGreaterOrEqual(today) &&
                    !eventDate.isGreater(end)) {
                eventList.add(e);
            }

        }
        return eventList;
    }

    public void listAllEventsUntil(Scanner in){
        List<Event> eventList = this.getAllEventsUntil(in);
        for(Event e: eventList){
            System.out.println(e);
        }
    }

// HELPERS

    public boolean isFestival(Event e){
        boolean isFestival = true;

        try{
            Festival f = (Festival)e;
        }
        catch(Exception ClassCastException){
            isFestival = false;
        }

        return isFestival;
    }

    public boolean isSectioned(Location l){
        boolean isSectioned = true;
        try{
            SectionedLocation sl = (SectionedLocation)l;
        }
        catch(Exception ClassCastException){
            isSectioned = false;
        }
        return isSectioned;
    }

    public Location readLocationForEvent(){
        Scanner in = new Scanner(System.in);
        System.out.println("Is the event happening at an existing location?(y/n): ");
        String ans = in.nextLine();
        Location l = null;
        if(ans.equals("y")){
            System.out.println("Enter location ID: ");
            int locID = Integer.parseInt(in.nextLine());
            l = this.getVenueByID(locID);
            if(l.getName() == null) {
                System.out.println("Invalid location ID. Please create a new location.");
                ans = "n";
            }
        }
        if(ans.equals("n")){
            System.out.println("Does the location have sectioned seats? (y/n)");
            ans = in.nextLine();
            if(ans.equals("y"))
                l = new SectionedLocation(in);
            else
                l = new Location(in);
        }

        this.locations.add(l);

        return l;
    }

    public Event eventExistsHelper(Scanner in){
        System.out.println("Enter the event ID: ");
        int eventID = Integer.parseInt(in.nextLine());
        Event event = this.getEventByID(eventID);
        if (event.getTitle() == null) {
            System.out.println("Please select a valid event or create one first!");
            return null;
        }
        return event;
    }

    public Client clientExistsHelper(Scanner in)
    {
        System.out.println("Enter the user's ID:");
        int userID = Integer.parseInt(in.nextLine());
        Client client = this.getClientByID(userID);
        if (client.getLastName() == null) {
            System.out.println("Please select a valid client or create one first!");
            return null;
        }
        return client;
    }

    public Client getClientByID(int ID){
        for(Client c: this.clients){
            if(c.getID() == ID){
                return c;
            }
        }
        Client error = new Client();
        Client.decreaseCounterOnError();
        return error;
    }

    public Event getEventByID(int ID) {
        for (Event e : this.events) {
            if (e.getID() == ID)
                return e;
        }
        Event error = new Event();
        Event.decreaseCounterOnError();
        return error;
    }

    public Location getVenueByID(int ID){
        for(Location l: this.locations) {
            if (l.getID() == ID)
                return l;
        }

        Location error = new Location();
        Location.decreaseCounterOnError();
        return error;
    }

    private String chooseDiscountCategory(Scanner in, Event event){

        System.out.println("There are discounts for:");
        for(String key: event.getDiscounts().keySet()){
            System.out.format("%s, ", key);
        }
        System.out.println("Please choose one of the categories");
        String cat = in.nextLine();
        if(event.getDiscount(cat) == 0){
            System.out.println("Discount category does not exist! Please try again!");
            return "";
        }
        return cat;
    }

}

//    public Client createClient(Client c) {
//
//        clients.add(c);
//        return c;
//    }
//
//    public SectionedLocation addSectionedLocation(String name, String city, String address, int capacity,
//                                                  int categories, HashMap<Integer, Integer> cc) {
//        SectionedLocation l = new SectionedLocation(name, city, address, capacity, categories, cc);
//
//        l.setCapacityForCategory(3, 1);
//        l.setCapacityForCategory(2, 1);
//        l.setCapacityForCategory(1, 1);
//
//        venues.add(l);
//        return l;
//    }
//
//    public Event createEvent(String t, Date d, Location l, int age, Time gate, Time start,
//                             HashMap<String, Integer> discounts) {
//        Event e = new Event(t, d, l, age, gate, start, discounts);
//        events.add(e);
//        return e;
//    }
//
//    public Festival createFestival(String t, Date d, Location l, int age, Time gate, Time start,
//                                   Date e, HashMap<String, Integer> discounts) {
//        Festival f = new Festival(t, d, l, age, gate, start, e, discounts);
//        events.add(f);
//        return f;
//    }

