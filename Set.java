import java.util.ArrayList;

public class Set extends Location {
    private int shot_counters;
    private int max_shot_counters;
    private Scene set_scene;
    private ArrayList<Role> extra_roles;

    public Set(String name, ArrayList<String> nearby_locations, int shot_counters, int max_shot_counters, ArrayList<Role> extra_roles){
        super(name, nearby_locations);
        this.shot_counters = shot_counters;
        this.max_shot_counters = max_shot_counters;
        this.set_scene = set_scene;
        this.extra_roles = extra_roles;
    }   


    // -----Accessors-----

    public int getShotCounters(){
        return shot_counters;
    }

    public Scene getScene(){
        return set_scene;
    }

    public ArrayList<Role> getExtraRoles(){
        return extra_roles;
    }

    // -----Mutators-----

    public void setShotCounters(int counters){
        this.shot_counters = counters;
    }

    public void setScene(Scene scene){
        this.set_scene = scene;
    }
}
