package Entities.Ticket;
import Entities.Client.Client;
import Entities.Date.*;
import Entities.Event.*;
import java.util.Scanner;

public class DayPass extends Ticket{
    private Date validity;

    public DayPass(Event event, Client client, double price, int cat, boolean discount, Date validity){
        super(event, client, price, cat, discount);
        this.validity = this.getEvent().getDate();
    }

    public DayPass(int id, Event event, Client client, double price, int cat, Date validity){
        super(id, event, client, price, cat);
        this.validity = this.getEvent().getDate();
    }

    public DayPass(Scanner in, int category, Event event, Client client, boolean discount){
        super(in, category, event, client, discount);
        this.validity = this.getEvent().getDate();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }
}
