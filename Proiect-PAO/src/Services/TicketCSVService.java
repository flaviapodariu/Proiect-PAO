package Services;

import Entities.Date.Date;
import Entities.Date.Time;
import Entities.Event.Event;
import Entities.Ticket.DayPass;
import Entities.Ticket.FullPass;
import Entities.Ticket.LimitedPass;
import Entities.Ticket.Ticket;
import com.sun.tools.javac.Main;

import javax.management.openmbean.TabularData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketCSVService {
    public static TicketCSVService instance = null;
    final private List<Ticket> tickets = new ArrayList<>();
    private TicketCSVService(){

    }

    public static TicketCSVService getInstance(){
        if(instance == null)
            instance = new TicketCSVService();
        return instance;
    }

    public List<Ticket> getFromCSV(String file) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(file));
        String line;

        while((line =  data.readLine()) != null){
            line = line.replace(" ", "");
            String [] tData = line.split(",");
            Ticket ticket = null;
            switch (tData.length){
                case 4 -> ticket = fullPass(tData);
                case 5 -> ticket = dayPass(tData);
                case 6 -> ticket = limitedPass(tData);
            }
            this.tickets.add(ticket);
        }

        return this.tickets;
    }

    public Ticket fullPass(String[] tData){
        return new FullPass(
                MainService.getInstance().getEventByID(Integer.parseInt(tData[0])),
                Double.parseDouble(tData[1]),
                Boolean.parseBoolean(tData[2]),
                Integer.parseInt(tData[3]));
    }

    public Ticket dayPass(String[] tData){
        return new DayPass(
                MainService.getInstance().getEventByID(Integer.parseInt(tData[0])),
                Double.parseDouble(tData[1]),
                Integer.parseInt(tData[2]),
                Boolean.parseBoolean(tData[3]),
                Date.parser(tData[4]));
    }

    public Ticket limitedPass(String[] tData){
        return new LimitedPass(
                MainService.getInstance().getEventByID(Integer.parseInt(tData[0])),
                Double.parseDouble(tData[1]),
                Integer.parseInt(tData[2]),
                Boolean.parseBoolean(tData[3]),
                Integer.parseInt(tData[4]),
                Time.parser(tData[5]));
    }

}
