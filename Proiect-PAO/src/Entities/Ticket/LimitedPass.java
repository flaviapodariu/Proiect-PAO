package Entities.Ticket;

import Entities.Client.Client;
import Entities.Date.*;
import Entities.Event.*;

import javax.swing.text.html.StyleSheet;
import java.util.Scanner;

public class LimitedPass extends Ticket{
    private int hours;
    private Time start;

    public LimitedPass(Event event, Client client, double price, int cat, boolean discount, int h, Time start){
        super(event, client, price, cat, discount);
        this.hours = h;
        this.start = start;
    }
    public LimitedPass(int id, Event event, Client client, double price, int cat, int h, Time start){
        super(id, event, client, price, cat);
        this.hours = h;
        this.start = start;
    }
    public LimitedPass(Scanner in, int category, Event event, Client client, boolean discount){
        super(in, category, event, client, discount);
        this.read(in);
    }

    public void read(Scanner in){
        System.out.println("Number of hours valid: ");
        this.hours = Integer.parseInt(in.nextLine());
        System.out.println("Ticket valid from (hh:mm): ");
        this.start = Time.parser(in.nextLine());
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

}
