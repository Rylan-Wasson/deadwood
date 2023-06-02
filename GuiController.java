import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * GuiController
 */
public class GuiController {
    private BoardView view;
    private boardMouseListener listener;

    public GuiController(ArrayList<Scene> scenes, ArrayList<Location> locations){
        listener = new boardMouseListener();
        view = new BoardView(listener);
        listener.setView(view);
        view.setVisible(true);
        createCovers(locations);
        createScenes(scenes);
    }

    // create all scene graphical objects
    private void createScenes(ArrayList<Scene> scenes){
        for(int i = 0; i < scenes.size(); i++){
            Scene scene = scenes.get(i);
            view.buildCard(scene.getImg());
        }
    }

    private void createCovers(ArrayList<Location> locations){
        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            view.addCoverCard(location.getName());
        }
    }

    // distribute graphic scene objects, as well as cover objects
    public void distributeScenes(ArrayList<Location> locations){
        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            if(location instanceof Set){
                Set set = (Set) location;
                view.putCard(set.getScene().getImg(), set.getX(), set.getY());
                view.putCoverCard(set.getName(), set.getX(), set.getY());
            }
        }
    }

    // given player object, update gui listed info for the player
    public void updatePlayerInfo(Player player){
        view.updatePlayerInfo(player.getPlayerID(), player.getCash(), player.getCredits(), player.getRehearseChips());
    }

    public String getPlayerCount(){
        return view.getPlayerCount();
    }

    public void initPlayers(int num_players){
        view.buildPlayers(num_players);
    }

    public void buildShots(ArrayList<Location> locations){
        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            if(location instanceof Set){
                Set set = (Set) location;
                ArrayList<ShotCounter> counters = set.getShotCounters();
                for(int j = 0; j < counters.size(); j++){
                    ShotCounter counter = counters.get(j);
                    String name = set.getName() + "" + counter.getNum(); // build shot name
                    view.buildShot(name);
                }
            }
        }
    }

    public void distributeShotCounters(ArrayList<Location> locations){
        for(int i = 0; i < locations.size(); i++){
            Location location = locations.get(i);
            if(location instanceof Set){
                Set set = (Set) location;
                ArrayList<ShotCounter> counters = set.getShotCounters();
                for(int j = 0; j < counters.size(); j++){
                    ShotCounter counter = counters.get(j);
                    String name = set.getName() + "" + counter.getNum(); // build shot name
                    view.putShot(name, counter.getX(), counter.getY());
                }
            }
        }
    }

    public void displayMessage(String message){
        view.displayMessage(message);
    }
}