package Services;

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

public class LocationCSVService {
    public static LocationCSVService instance = null;
    private List<Location> locations = new ArrayList<>();

    private LocationCSVService(){}

    public static LocationCSVService getInstance(){
        if(instance == null)
            instance = new LocationCSVService();
        return instance;
    }

    public List<Location> getFromCSV(String file) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(file));
        String line;

        while((line =  data.readLine()) != null){
            line = line.replace(" ", "");
            String [] cData = line.split(",");
            Location location = new Location(
                    cData[0],   // name
                    cData[1],  // city
                    cData[2], // address
                    Integer.parseInt(cData[3])); // capacity
            this.locations.add(location);
        }

        return this.locations;
    }
}
