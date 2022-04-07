package Ticket;
import Event.Event;

import java.util.Scanner;

abstract public class Ticket {
    private static int ticketUID = 1000;

    private int uniqueID;
    private Event event;
    private double fullPrice;
    private int category;
    private boolean discountsApplied;

    public Ticket(){
        this.uniqueID = ticketUID;
        ticketUID += 1;
        this.event = new Event();
        this.fullPrice = 0;
        this.category = 1;
        this.discountsApplied = false;
    }

    public Ticket(Event event, double fullPrice, int cat, boolean disc){
        this.uniqueID = ticketUID;
        ticketUID += 1;
        this.event = event;
        this.fullPrice = fullPrice;
        this.category = cat;
        this.discountsApplied = disc;
    }
    public Ticket(Scanner in, int category, Event e, boolean disc){
        this.readBase(in, category, e, disc);
    }

    public void readBase(Scanner in, int category, Event e, boolean discount){
        this.event = e;
        System.out.println("Full Price:");
        this.fullPrice = Integer.parseInt(in.nextLine());
        this.category = category;
        this.discountsApplied = discount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPriceWithDiscount(int discount){
        return this.fullPrice - (this.fullPrice * discount) / 100;
    }

    @Override
    public String toString() {
        return "Ticket #" + uniqueID + "-> " + this.event;
    }

}
