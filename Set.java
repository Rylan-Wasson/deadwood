import java.util.ArrayList;

public class Set {
    private String name;
    private ArrayList<Set> nearby_sets;
    private int shot_counters;
    private Scene set_scene;

    public Set(String name, ArrayList<Set> nearby_sets, int shot_counters, Scene set_scene){

    }


    // -----Accessors-----

    public String getName(){
        return name;
    }

    public ArrayList<Set> getNearbySets(){
        return nearby_sets;
    }

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
