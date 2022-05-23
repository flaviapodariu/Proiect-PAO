package Entities.Location;
import java.util.Scanner;

public class Location implements Comparable<Location> {
    private static int locUID = 100;

    private int locationID;
    private String name;
    private String city;
    private String address;
    private int capacity;

    public Location(){locUID += 1;}
    public Location(String name, String city, String address, int capacity){
        this.locationID = locUID;
        locUID += 1;
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
    }
    public Location(int id, String name, String city, String address, int capacity){
        this.locationID = id;
        locUID += 1;
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
    }
    public Location(Scanner in){
        this.readBase(in);
        this.locationID = locUID;
        locUID += 1;
    }

    protected void readBase(Scanner in){
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("City: ");
        this.city = in.nextLine();
        System.out.println("Address: ");
        this.address = in.nextLine();
        System.out.println("Capacity: ");
        this.capacity = Integer.parseInt(in.nextLine());
    }

    public int getID(){ return locationID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable(int amount) {
        return this.getCapacity() >= amount;
    }

    public static void decreaseCounterOnError(){ locUID -= 1;}

    @Override
    public int compareTo(Location l){
        return this.capacity - l.capacity;
    }
    @Override
    public String toString() {
        return "Location " + this.name + " in " +
                this.city + " has a capacity of " +  this.capacity;
    }
}
