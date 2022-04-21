package Entities.Ticket;
import Entities.Date.*;
import Entities.Event.*;
import java.util.Scanner;

public class DayPass extends Ticket{
    private Date validity;

    public DayPass(Event event, double price, int cat, boolean discount, Date validity){
        super(event, price, cat, discount);
    }

    public DayPass(Scanner in, int category, Event event, boolean discount){
        super(in, category, event, discount);
        this.read(in);
    }

    public void read(Scanner in){
        System.out.println("Enter the date on which the ticket is valid(d-m-yyyy): ");
        this.validity = Date.parser(in.nextLine());
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }
}
