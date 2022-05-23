package Services;

import Entities.Date.Date;
import Entities.Date.Time;
import Entities.Ticket.DayPass;
import Entities.Ticket.FullPass;
import Entities.Ticket.LimitedPass;
import Entities.Ticket.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        MainService ms = MainService.getInstance();
        return new FullPass(
                ms.getEventByID(Integer.parseInt(tData[0])),
                ms.getClientByID(Integer.parseInt(tData[1])),
                Double.parseDouble(tData[2]),
                Boolean.parseBoolean(tData[3]),
                Integer.parseInt(tData[4]));
    }

    public Ticket dayPass(String[] tData){
        MainService ms = MainService.getInstance();
        return new DayPass(
                ms.getEventByID(Integer.parseInt(tData[0])),
                ms.getClientByID(Integer.parseInt(tData[1])),
                Double.parseDouble(tData[2]),
                Integer.parseInt(tData[3]),
                Boolean.parseBoolean(tData[4]),
                Date.parser(tData[5]));
    }

    public Ticket limitedPass(String[] tData){
        MainService ms = MainService.getInstance();
        return new LimitedPass(

                ms.getEventByID(Integer.parseInt(tData[0])),
                ms.getClientByID(Integer.parseInt(tData[1])),
                Double.parseDouble(tData[2]),
                Integer.parseInt(tData[3]),
                Boolean.parseBoolean(tData[4]),
                Integer.parseInt(tData[5]),
                Time.parser(tData[6]));
    }

}
