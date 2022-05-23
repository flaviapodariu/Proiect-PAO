package Queries;

import Entities.Client.Client;
import Entities.Date.Date;
import Entities.Event.Event;
import Entities.Ticket.DayPass;
import Entities.Ticket.Ticket;
import Services.MainService;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DayPassQueries {
    private Connection conn;
    private MainService ms;

    public DayPassQueries(Connection conn, MainService service){
        this.conn = conn;
        this.ms = service;
    }

//    CREATE
    public void insert(DayPass pass) throws SQLException {
        int id = pass.getUniqueID();
        int ev_id = pass.getEvent().getID();
        int cl_id = pass.getClient().getID();
        double price = pass.getFullPrice();
        int categ = pass.getCategory();
        String validity = "'" + pass.getEvent().getDate() + "'";
        String qrySQL = "INSERT INTO Daypass VALUES( " + id + ", " + ev_id + ", " + cl_id + ", " +
                price + ", " + categ + ", " + validity + ")";

        Statement s = this.conn.createStatement();
        s.executeQuery(qrySQL);
    }
//    READ

    public void get() throws SQLException{
        String qrySQL = "SELECT * FROM Daypass";
        Statement s = this.conn.createStatement();
        List<Ticket> t_lst = new ArrayList<>();
        ResultSet rs = s.executeQuery(qrySQL);
        while(rs.next()){
            Event e = this.ms.getEventByID(rs.getInt(2));
            Client c = this.ms.getClientByID(rs.getInt(3));
            DayPass t = new DayPass(rs.getInt(1), e, c, rs.getDouble(4),
                    rs.getInt(5), Date.parser(rs.getString(6)));
            t_lst.add(t);
        }
        this.ms.setTicketsSold(t_lst);
    }

//    UPDATE
    public void update(Date validUntil, int id) throws SQLException {
        String qrySQL = "UPDATE Daypass " +
                        "SET validity=? " +
                        "WHERE ticket_id=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setString(1, validUntil.toString());
        ps.setInt(2, id);
        ps.executeUpdate();

    }


//    DELETE
    public void delete(int ticket_id) throws SQLException {
        String qrySQL = "DELETE FROM Daypass " +
                        "WHERE ticket_id=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setInt(1, ticket_id);
        ps.executeUpdate();
        this.ms.deletePassById(ticket_id);
    }
}
