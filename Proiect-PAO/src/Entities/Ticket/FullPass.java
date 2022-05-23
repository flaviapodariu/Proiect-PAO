package Entities.Ticket;

import Entities.Client.Client;
import Entities.Event.*;

import java.util.Scanner;

public class FullPass extends Ticket{

    public FullPass(Event event, Client client, double price, boolean discount, int category){
        super(event, client, price, category, discount);
    }
    public FullPass(int id, Event event, Client client, double price, int category){
        super(id, event, client, price, category);
    }

    public FullPass(Scanner in, int category, Event event, Client client, boolean discount){
        super(in, category, event, client, discount);
    }
}
