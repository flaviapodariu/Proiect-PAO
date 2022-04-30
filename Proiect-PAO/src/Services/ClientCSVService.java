package Services;

import Entities.Client.Client;
import Entities.Date.Date;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;

public class ClientCSVService {
    public static ClientCSVService instance = null;
    final private List<Client> clients = new ArrayList<>();

    private ClientCSVService(){}

    public static ClientCSVService getInstance(){
        if(instance == null)
            instance = new ClientCSVService();
        return instance;
    }

    public List<Client> getFromCSV(String file) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(file));
        String line;

        while((line =  data.readLine()) != null){
            line = line.replace(" ", "");
            String [] cData = line.split(",");
            Client client = new Client(cData[0], cData[1], Date.parser(cData[2]));
            this.clients.add(client);
        }

        return this.clients;
    }
}
