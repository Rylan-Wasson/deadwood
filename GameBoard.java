import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private int numActiveScenes;
    private ArrayList<Location> locations;

    public GameBoard(ArrayList<Location> locations){
        this.locations = locations;
    }

    public int getNumActiveScenes(){
        return this.numActiveScenes;
    }

    public void setNumScenes(int numScenes){
        numActiveScenes = numScenes;
    }

    public void distributeScenes(ArrayList<Scene> scenes){
        Collections.shuffle(scenes);

        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            if(location instanceof Set){
                Set set = (Set) location;
                set.setShotCounters(set.getMaxShotCounters());
                set.setScene(scenes.get(i));
            }
        }
        numActiveScenes = 10;
    }
    /* returns location object with locationname name, null if it dne */
    public Location getLocationByName(String name){
        for(int i = 0; i < locations.size(); i++){
            if(locations.get(i).getName().equalsIgnoreCase(name)){
                return locations.get(i);
            }
        }
        return null;
    }
}
