import java.util.ArrayList;

public class Set extends Location {
    private ArrayList<ShotCounter> counters;
    private int num_counters;
    private int max_shot_counters;
    private Scene set_scene;
    private ArrayList<Role> extra_roles;

    public Set(String name, ArrayList<String> nearby_locations, ArrayList<ShotCounter> shot_counters, int max_shot_counters, ArrayList<Role> extra_roles, int x, int y, int width, int height){
        super(name, nearby_locations, x, y, width, height);
        this.counters = shot_counters;
        this.num_counters = max_shot_counters;
        this.max_shot_counters = max_shot_counters;
        this.extra_roles = extra_roles;
    }   


    // -----Accessors-----

    public int getShotCounters(){
        return counters.size();
    }

    public Scene getScene(){
        return set_scene;
    }

    public ArrayList<Role> getExtraRoles(){
        return extra_roles;
    }

    public int getMaxShotCounters(){
        return max_shot_counters;
    }

    // -----Mutators-----

    public void decrementShotCounters(){
        this.counters.remove(this.counters.size()-1);
        this.num_counters -= 1;
    }

    public void setShotCounters(int num){
        this.num_counters = num;
    }

    public void setScene(Scene scene){
        this.set_scene = scene;
    }
}
