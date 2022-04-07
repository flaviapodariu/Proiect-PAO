package Ticket;

import Date.Date;
import Event.Event;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FullPass extends Ticket{

    public FullPass(Event event, double price, boolean discount, int category){
        super(event, price, category, discount);
    }

    public FullPass(Scanner in, int category, Event event, boolean discount){
        super(in, category, event, discount);
    }
}
