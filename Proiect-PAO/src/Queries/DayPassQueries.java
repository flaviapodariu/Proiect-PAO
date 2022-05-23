package Queries;

import Entities.Ticket.DayPass;
import Services.MainService;

import java.sql.Connection;

public class DayPassQueries {
    private Connection conn;
    private MainService ms;

    public DayPassQueries(Connection conn, MainService service){
        this.conn = conn;
        this.ms = service;
    }

    public void insert(DayPass pass){

    }
}
