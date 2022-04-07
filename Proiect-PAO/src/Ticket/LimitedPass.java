package Ticket;

import Date.Time;
import Event.Event;

import javax.swing.text.html.StyleSheet;
import java.util.Scanner;

public class LimitedPass extends Ticket{
    private int hours;
    private Time start;

    public LimitedPass(Event event, double price, int cat, boolean discount, int h, Time start){
        super(event, price, cat, discount);
        this.hours = h;
        this.start = start;
    }
    public LimitedPass(Scanner in, int category, Event event, boolean discount){
        super(in, category, event, discount);
        this.read(in);
    }

    public void read(Scanner in){
        System.out.println("Number of hours valid: ");
        this.hours = Integer.parseInt(in.nextLine());
        System.out.println("TIcket valid from (hh:mm): ");
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
