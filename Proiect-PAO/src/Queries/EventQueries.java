package Queries;

import Entities.Date.Date;
import Entities.Date.Time;
import Entities.Event.Event;
import Entities.Location.Location;
import Entities.Date.*;
import Services.MainService;
import com.sun.tools.javac.Main;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class EventQueries {
    private Connection conn;
    private MainService ms;

    public EventQueries(Connection conn, MainService service){
        this.conn = conn;
        this.ms = service;
    }

//    CREATE
    public void insert(Event ev) throws SQLException{
        int id = ev.getID();
        String title = "'" + ev.getTitle() + "'";
        String ev_date = "'" + ev.getDate().toString() + "'";
        int location_id = ev.getVenue().getID();
        int minAge = ev.getMinAge();
        String gate_time = "'" + ev.getGateTime().toString() + "'";
        String start_time = "'" + ev.getStartingTime().toString() +"'";

        Statement st = this.conn.createStatement();

        String qrySQL = "INSERT INTO Event VALUES(" + id + ", " + title + ", " + ev_date +
                ", "  + location_id + ", " + minAge + ", "  + gate_time + ", " + start_time + ")";

        st.executeUpdate(qrySQL);
    }

//    READ
    public void get() throws SQLException {
        Statement statement = this.conn.createStatement();
        String qrySQL = "SELECT * FROM Event e";
        ResultSet rs = statement.executeQuery(qrySQL);
        List<Event> ev_lst = new ArrayList<>();

        while(rs.next()){
            Location ev_l = this.ms.getVenueByID(rs.getInt("location_id"));
            Event ev = new Event(rs.getInt(1), rs.getString(2),
                    Date.parser(rs.getString(3)), ev_l, rs.getInt(5),
                    Time.parser(rs.getString(6)), Time.parser(rs.getString(7)));
            ev_lst.add(ev);
        }

        this.ms.setEvents(ev_lst);
    }

//    UPDATE
    public void update_date(String ev_name, Date date) throws SQLException {
        String qrySQL = "UPDATE Event" +
                        " SET ev_date=?" +
                        "WHERE title=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setString(1, date.toString());
        ps.setString(2, ev_name);
        ps.executeUpdate();
        this.ms.updateEventDateByName(ev_name, date);
    }

//    DELETE
    public void delete(String ev_title) throws SQLException {
        String qrySQL = "DELETE FROM Event WHERE title=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setString(1, ev_title);
        ps.executeUpdate();
        this.ms.deleteEventByName(ev_title);
    }
}
