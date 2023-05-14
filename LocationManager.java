import java.util.HashMap;
import java.util.ArrayList;

public class LocationManager {
    private HashMap<Integer, Location> player_locations;
    private GameBoard gb;
    private GameManager gameManager;

    public LocationManager(GameBoard gb, GameManager gameManager){
        this.gb = gb;
        this.gameManager = gameManager;
        player_locations = new HashMap<Integer, Location>();
    }
    public void decrementNumScenes(){
        int scenes = gb.getNumActiveScenes();
        gb.setNumScenes(--scenes); 
    }
    public Location getLocationByID(int ID){
        return player_locations.get(ID);
    }

    public Location getLocationByName(String name){
        return gb.getLocationByName(name);
    }

    public ArrayList<Player> getPlayersByLocation(Location location){
        ArrayList<Player> players = gameManager.getPlayers();
        ArrayList<Player> players_at_location = new ArrayList<Player>();

        for(int i = 0; i < players.size(); i++){
            if(player_locations.get(players.get(i).getPlayerID()).equals(location)){
                players_at_location.add(players.get(i));
            }
        }
        return players_at_location;
    }

    public void updateLocation(int ID, Location location){
        player_locations.put(ID, location);
    }

    public void moveAllPlayers(ArrayList<Player> players, Location location){
        for(int i = 0; i < players.size(); i++){
            player_locations.put(players.get(i).getPlayerID(), location);
        }
    }
}
