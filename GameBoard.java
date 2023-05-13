import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    private final int scene_count = 10;
    private int numActiveScenes;
    private ArrayList<Location> locations;

    public GameBoard(ArrayList<Location> locations){
        this.locations = locations;
    }

    public int getNumActiveScenes(){
        return this.numActiveScenes;
    }

    public void distributeScenes(ArrayList<Scene> scenes){
        Collections.shuffle(scenes);

        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            if(location instanceof Set){
                Set set = (Set) location;
                set.setScene(scenes.get(i));
            }
        }
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

    //TODO: remove after testing
    public void printLocations(){
        for(int i = 0; i < locations.size(); i++){
            System.out.println("Location: " + i + " " + locations.get(i).getName());
            for(int j = 0; j < locations.get(i).getAdjacentLocations().size(); j++){
                System.out.println("    --->ADJ Location: " + locations.get(i).getAdjacentLocations().get(j));
            }
        }
    }
}
