package Entities.Ticket;
import Entities.Client.Client;
import Entities.Event.*;

import java.util.Scanner;

abstract public class Ticket {
    private static int ticketUID = 1000;

    private int uniqueID;
    private Event event;
    private Client client;
    private double fullPrice;
    private int category;
    private boolean discountsApplied;

    public Ticket(){
        this.uniqueID = ticketUID;
        ticketUID += 1;
        this.event = new Event();
        this.client = new Client();
        this.fullPrice = 0;
        this.category = 1;
        this.discountsApplied = false;
    }

    public Ticket(Event event, Client client, double fullPrice, int cat, boolean disc){
        this.uniqueID = ticketUID;
        ticketUID += 1;
        this.event = event;
        this.client = client;
        this.fullPrice = fullPrice;
        this.category = cat;
        this.discountsApplied = disc;
    }
    public Ticket(int id, Event event, Client client, double fullPrice, int cat){
        this.uniqueID = id;
        ticketUID += 1;
        this.event = event;
        this.client = client;
        this.fullPrice = fullPrice;
        this.category = cat;
        this.discountsApplied = false;
    }
    public Ticket(Scanner in, int category, Event e, Client c, boolean disc){
        this.readBase(in, category, e, c, disc);
    }

    public void readBase(Scanner in, int category, Event e, Client c, boolean discount){
        this.uniqueID = ticketUID;
        ticketUID += 1;
        this.event = e;
        this.client = c;
        System.out.println("Full Price:");
        this.fullPrice = Integer.parseInt(in.nextLine());
        this.category = category;
        this.discountsApplied = discount;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
