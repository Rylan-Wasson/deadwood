import java.util.ArrayList;

public class CastingOffice extends Location {
    private ArrayList<Upgrade> upgrades;

    public CastingOffice(String name, ArrayList<String> adjacent_locations, ArrayList<Upgrade> upgrades){
        super(name, adjacent_locations);
        this.upgrades = upgrades;
    }
    
    public void upgradePlayer(Player player){
        
    }
}
