import java.util.ArrayList;

public class CastingOffice extends Location {
    private ArrayList<Upgrade> upgrades;

    public CastingOffice(String name, ArrayList<String> adjacent_locations, ArrayList<Upgrade> upgrades, int x, int y, int width, int height){
        super(name, adjacent_locations, x, y, width, height);
        this.upgrades = upgrades;
    }
    
    public ArrayList<Upgrade> getUpgrades(){
        return upgrades;
    }
}
