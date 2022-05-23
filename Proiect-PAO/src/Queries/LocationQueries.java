package Queries;
import Entities.Location.Location;
import Services.MainService;
import com.sun.tools.javac.Main;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationQueries {
    private Connection conn;
    private MainService ms;

    public LocationQueries(Connection c, MainService service){
        this.conn = c;
        this.ms = service;
    }

//    CREATE
    public void insert(Location l) throws SQLException {
        int id = l.getID();
        String name = "'" + l.getName() + "'";
        String city = "'" + l.getCity() + "'";
        String address = "'" + l.getAddress() + "'";
        int capacity = l.getCapacity();
        Statement statement = this.conn.createStatement();

        String qrySQL = "INSERT INTO Location VALUES(" + id + ", " + name + ", " + city +
                ", " + address + ", " + capacity + ")";

        statement.executeUpdate(qrySQL);
    }

//    READ
    public void get() throws SQLException {
        String qrySQL = "SELECT * FROM Location";
        Statement st = this.conn.createStatement();
        List<Location> loc_lst =  new ArrayList<>(); // for ids to work fine
        ResultSet rs = st.executeQuery(qrySQL);
        while(rs.next()){
            Location l = new Location(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5));
            loc_lst.add(l);
        }
        this.ms.setLocations(loc_lst);
    }


//  UPDATE
    public void update_capacity(int new_capacity, int loc_id) throws SQLException {
        String qrySQL = "UPDATE Location " +
                        "SET capacity=?" +
                        "WHERE location_id=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setInt(1, new_capacity);
        ps.setInt(2, loc_id);
        ps.executeUpdate();
        this.ms.updateLocationCapacity(new_capacity, loc_id);
    }

//    DELETE
    public void delete(int loc_id) throws SQLException{
        String qrySQL = "DELETE FROM Location " +
                        "WHERE location_id=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setInt(1, loc_id);
        ps.executeUpdate();
        this.ms.deleteLocationByID(loc_id);

    }
}
