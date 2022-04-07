package Comparators;

import Location.Location;

import java.util.Comparator;

public class LocationNameComparator implements Comparator<Location> {

    @Override
    public int compare(Location l1, Location l2){
        return l1.getName().compareTo(l2.getName());
    }


}
