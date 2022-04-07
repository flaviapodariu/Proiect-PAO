package Event;

import Date.*;
import Location.Location;

import java.util.HashMap;
import java.util.Scanner;

public class Festival extends Event{
    private Date endDate;

    public Festival(){}
    public Festival(String t, Date d, Location l, int age, Time gate, Time start, Date end, HashMap<String, Integer> discounts){
        super(t, d, l, age, gate, start, discounts);
        this.endDate = end;
    }
    public Festival(Scanner in, Location l){
        super(in, l);
        this.readFestival(in);
    }

    public void readFestival(Scanner in){
        System.out.println("End date: ");
        this.endDate = Date.parser(in.nextLine());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Festivalul " + this.getTitle() + " incepe pe " +
                this.getDate() + " si se termina pe " + this.endDate;
    }
}
