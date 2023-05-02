import java.util.ArrayList;

public class Location {
    protected String name;
    protected ArrayList<Location> nearby_locations;


    public Location(String name, ArrayList<Location> locations){
        this.name = name;
        this.nearby_locations = locations;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Location> getNearbyLocations(){
        return nearby_locations;
    }
}
