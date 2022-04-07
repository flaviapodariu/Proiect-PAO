package Comparators;

import Location.Location;

import java.util.Comparator;

public class CapacityComparator implements Comparator<Location> {

    @Override
    public int compare(Location l1, Location l2){
        int res = l2.getCapacity() - l1.getCapacity();  //desc
        if(res != 0)
            return res;
        else{
          return l1.getName().compareTo(l2.getName());
        }
    }
}
