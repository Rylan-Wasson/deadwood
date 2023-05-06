import java.util.ArrayList;

public class Location {
    protected String name;
    protected ArrayList<Location> adjacent_locations;


    public Location(String name, ArrayList<Location> adjacent_locations){
        this.name = name;
        this.adjacent_locations = adjacent_locations;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Location> getAdjacentLocations(){
        return adjacent_locations;
    }
}
