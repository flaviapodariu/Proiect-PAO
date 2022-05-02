package Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteService {
   public static WriteService instance = null;
   private WriteService(){

   }
   public static WriteService getInstance(){
      if(instance == null)
         instance = new WriteService();
      return instance;
   }

   public <T> void writeToFile(String fileName, List<T> objects) throws IOException {
       BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

       objects.forEach(item -> {
           try {
               out.write(item.toString());
           }
           catch (IOException e) {
               e.printStackTrace();
           }
       });
       out.flush();
      }
   }

