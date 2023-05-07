import java.util.ArrayList;

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
        
    }
}
