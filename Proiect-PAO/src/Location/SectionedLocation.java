package Location;

import java.util.HashMap;
import java.util.Scanner;

public class SectionedLocation extends Location{
    private int categories;
    private HashMap<Integer, Integer> categCapacities;

    public SectionedLocation(String name, String city, String address, int capacity,
                             int categories, HashMap<Integer, Integer> cc){
        super(name, city, address, capacity);
        this.categories = categories;
        this.categCapacities = cc;
    }
    public SectionedLocation(Scanner in){
        super(in);
        this.read(in);
    }

    public void read(Scanner in){
        System.out.println("Number of categories: ");
        this.categories = Integer.parseInt(in.nextLine());
        this.categCapacities = new HashMap<Integer, Integer>();
        for(int i = 1; i <= this.categories; i++)
        {
            System.out.format("Price for category %d: ", i);
            categCapacities.put(i, Integer.parseInt(in.nextLine()));
        }
    }

    public HashMap<Integer, Integer> getCategCapacities() {
        return categCapacities;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public void setCapacityForCategory(Integer category, Integer capacity) {
        this.categCapacities.put(category, capacity);
    }

    public boolean isAvailable(int category, int amount){
        if(categCapacities.get(category) - amount < 0)
            return false;
        categCapacities.put(category, categCapacities.get(category) - amount);
        return true;
    }
}
