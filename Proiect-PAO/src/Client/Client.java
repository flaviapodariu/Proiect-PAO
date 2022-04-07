package Client;

import Date.Date;
import Ticket.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static int clientUID = 400;

    private int uniqueID;
    private String firstName;
    private String lastName;
    private Date DOB;
    private List<Ticket> tickets;

    public Client(){
        this.uniqueID = clientUID;
        clientUID += 1;
    }
    public Client(String first, String last, Date dob){
        this.uniqueID = clientUID;
        clientUID += 1;
        this.firstName = first;
        this.lastName = last;
        this.DOB = dob;
    }
    public Client(Scanner in){
        this.read(in);
        this.uniqueID = clientUID;
        clientUID += 1;
    }

    public void read(Scanner in){
        System.out.println("First name: ");
        this.firstName = in.nextLine();
        System.out.println("Last name: ");
        this.lastName = in.nextLine();
        System.out.println("Date of Birth\n Day: ");
        int d = Integer.parseInt(in.nextLine());
        System.out.println("\nMonth: ");
        int m = Integer.parseInt(in.nextLine());
        System.out.println("\nYear: ");
        int y = Integer.parseInt(in.nextLine());
        this.DOB = new Date(d, m, y);
    }

    @Override
    public String toString() {
        return "Client is " + this.firstName + " " +
                this.lastName + ".\nBought tickets are: " +  this.tickets.toString();
    }

    public int getID(){
        return this.uniqueID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public boolean validateAge(Date eventDate, int minAge){
        if(eventDate.getYear() - this.DOB.getYear() < minAge)
            return false;

        if(eventDate.getYear() - this.DOB.getYear() == minAge) {
            if (eventDate.getMonth() == this.DOB.getMonth()) {
                if (eventDate.getDay() < this.DOB.getDay())
                    return false;
                return true;
            }
            return eventDate.getMonth() > this.DOB.getMonth();

        }

        return true;
    }

    public static void decreaseCounterOnError(){
        clientUID -= 1;
    }

}
