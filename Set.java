import java.util.ArrayList;

public class Set extends Location {
    private int shot_counters;
    private Scene set_scene;

    public Set(String name, ArrayList<Location> nearby_locations, int shot_counters, Scene set_scene){
        super(name, nearby_locations);
        this.shot_counters = shot_counters;
        this.set_scene = set_scene;
    }


    // -----Accessors-----

    public int getShotCounters(){
        return shot_counters;
    }

    public Scene getScene(){
        return set_scene;
    }

    // -----Mutators-----

    public void setShotCounters(int counters){
        this.shot_counters = counters;
    }

    public void setScene(Scene scene){
        this.set_scene = scene;
    }
}
