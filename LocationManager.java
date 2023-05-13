import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class LocationManager {
    private HashMap<Integer, Location> player_locations;
    private GameBoard gb;

    public LocationManager(GameBoard gb){
        this.gb = gb;
        player_locations = new HashMap<Integer, Location>();
    }
    public Location getLocationByID(int ID){
        return player_locations.get(ID);
    }

    public Location getLocationByName(String name){
        return gb.getLocationByName(name);
    }

    public void updateLocation(int ID, Location location){
        player_locations.put(ID, location);
    }

    public void moveAllPlayers(LinkedList<Player> players, Location location){
        for(int i = 0; i < players.size(); i++){
            player_locations.put(players.get(i).getPlayerID(), location);
        }
    }
}
