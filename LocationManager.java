import java.util.HashMap;
import java.util.ArrayList;
public class LocationManager {
    private HashMap<Integer, Location> player_locations;
    private GameBoard gb;
    
    public Location getLocationByID(int ID){
        return player_locations.get(ID);
    }

    public Location getLocationByName(String name){
        return gb.getLocationByName(name);
    }

    public void updateLocation(int ID, Location location){
        player_locations.put(ID, location);
    }

    public void moveAllPlayers(Location location){

    }
}
