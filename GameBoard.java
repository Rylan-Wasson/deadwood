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
                System.out.println(set.getName() + " "+ set.getScene().getName());
            }
        }
    }
}
