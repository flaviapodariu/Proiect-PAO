package Queries;

import Entities.Client.Client;
import Entities.Date.Date;
import Services.MainService;
import com.sun.tools.javac.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientQueries {
    private Connection conn;
    private MainService ms;

    public ClientQueries(Connection c, MainService service){
        this.conn = c;
        this.ms = service;
    }

//    CREATE
    public void insert(Client client) throws SQLException {
        int id = client.getID();
        String fName = "'" + client.getFirstName() + "'";
        String lName = "'" + client.getLastName()+ "'";
        String dob = "'" + client.getDOB().toString() + "'";
        Statement statement = this.conn.createStatement();

        String qrySQL = "INSERT INTO Client VALUES( " +  id + ", " + fName + ", " + lName
                 + ", " + dob + ")";

        statement.executeUpdate(qrySQL);
    }

//    READ

    public void get() throws SQLException {
        Statement statement = this.conn.createStatement();
        String qrySQL = "SELECT * FROM Client";
        ResultSet rs = statement.executeQuery(qrySQL);
        List<Client> cl_lst = new ArrayList<>();

        while(rs.next()){
            Client c = (new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                    Date.parser(rs.getString(4))));
            cl_lst.add(c);
        }
        this.ms.setClients(cl_lst);
    }

//    UPDATE
    public void update(String firstN, int client_id) throws SQLException {
        String qrySQL = "UPDATE Client " +
                        "SET firstname=? " +
                        "WHERE client_id=? ";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setString(1, firstN);
        ps.setInt(2, client_id);
        ps.executeUpdate();
        this.ms.updateClientFirstName(firstN, client_id);
    }

//    DELETE
    public void delete(int id) throws SQLException {
        String qrySQL = "DELETE FROM Client " +
                "WHERE client_id=?";
        PreparedStatement ps = this.conn.prepareStatement(qrySQL);
        ps.setInt(1, id);
        ps.executeUpdate();
        this.ms.deleteClientByID(id);
    }


}
