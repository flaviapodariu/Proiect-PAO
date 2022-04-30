package Services;

import Entities.Client.Client;
import Entities.Date.Date;
import Entities.Date.Time;
import Entities.Event.Event;
import Entities.Location.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventCSVService {
    public static EventCSVService instance = null;
    final private List<Event> events = new ArrayList<>();
    private EventCSVService(){

    }

    public static EventCSVService getInstance(){
        if(instance == null)
            instance = new EventCSVService();
        return instance;
    }

    public List<Event> getFromCSV(String file) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(file));
        String line;

        while((line =  data.readLine()) != null){
            line = line.replace(" ", "");
            String [] cData = line.split(",");
            Event event = new Event(
                    cData[0],
                    Date.parser(cData[1]),
                    MainService.getInstance().getVenueByID(Integer.parseInt(cData[2])),
                    Integer.parseInt(cData[3]),
                    Time.parser(cData[4]),
                    Time.parser(cData[5]),
                    new HashMap<>());
            this.events.add(event);
        }

        return this.events;
    }
}
