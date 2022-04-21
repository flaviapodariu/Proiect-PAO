package Entities.Event;

import Entities.Date.*;
import Entities.Location.*;

import java.util.HashMap;
import java.util.Scanner;

public class Event {
    private static int eventUID = 2000;

    private final int uniqueID;
    private String title;
    private Date date;
    private Location venue;
    private int minAge;
    private Time gateTime;
    private Time startingTime;
    private HashMap<String, Integer> discounts;

    public Event(){
        this.uniqueID = eventUID;
        eventUID += 1;}

    public Event(String t, Date d, Location l, int age, Time gate, Time start, HashMap<String, Integer> discounts) {
        this.uniqueID = eventUID;
        eventUID += 1;
        this.title = t;
        this.date = d;
        this.venue = l;
        this.minAge = age;
        this.gateTime = gate;
        this.startingTime = start;
        this.discounts = discounts;
    }

    public Event(Scanner in, Location l){
        this.readEvent(in, l);
        this.uniqueID = eventUID;
        eventUID += 1;
    }

    public void readEvent(Scanner in, Location l){
        System.out.println("Enter the following details about the new event:");
        System.out.println("Title: ");
        this.title = in.nextLine();
        System.out.println("Date (d-m-yyyy): ");
        this.date = Date.parser(in.nextLine());
        this.venue = l;
        System.out.println("Minimum age: ");
        this.minAge = Integer.parseInt(in.nextLine());
        System.out.println("Gate time(hh:mm) : ");
        this.gateTime = Time.parser(in.nextLine());
        System.out.println("Starting time(hh:mm) : ");
        this.startingTime = Time.parser(in.nextLine());
        System.out.println("Number of discounts: ");
        int discN = Integer.parseInt(in.nextLine());
        this.discounts = new HashMap<String, Integer>();
        for(int i = 0; i < discN; i++){
            System.out.println("Discount is for (ex: children): ");
            String cat = in.nextLine();
            System.out.println("Discount in percentage: ");
            Integer perc = Integer.parseInt(in.nextLine());
            this.discounts.put(cat, perc);
        }


    }

    public int getID(){
        return this.uniqueID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getVenue() {
        return venue;
    }

    public void setVenue(Location venue) {
        this.venue = venue;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Time getGateTime() {
        return gateTime;
    }

    public void setGateTime(Time gateTime) {
        this.gateTime = gateTime;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    public HashMap<String, Integer> getDiscounts() {
        return discounts;
    }
    public int getDiscount(String discountCategory){
        if(this.discounts.containsKey(discountCategory))
            return this.discounts.get(discountCategory);
        return 0;
    }

    public void setDiscounts(HashMap<String, Integer> discounts) {
        this.discounts = discounts;
    }
    public void updateDiscount(String category, int percentage){
        this.discounts.put(category, percentage);
    }

    public static void decreaseCounterOnError(){
        eventUID -= 1;
    }

    @Override
    public String toString() {
        return this.title + "@" + this.venue.getName() + " " + this.venue.getCity() + " " + this.date;
    }
}
