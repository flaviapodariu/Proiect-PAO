package Entities.Ticket;

import Entities.Event.*;

import java.util.Scanner;

public class FullPass extends Ticket{

    public FullPass(Event event, double price, boolean discount, int category){
        super(event, price, category, discount);
    }

    public FullPass(Scanner in, int category, Event event, boolean discount){
        super(in, category, event, discount);
    }
}
