import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * GuiController
 */
public class GuiController {
    private BoardView view = null;
    private boardMouseListener listener;
    private GameManager gameManager;
    private TurnManager turnManager;

    public GuiController(ArrayList<Scene> scenes, ArrayList<Location> locations, GameManager gameManager){
        this.gameManager = gameManager;
        listener = new boardMouseListener(this);
        view = new BoardView(listener);
        listener.setView(view);
        view.setVisible(true);
        createCovers(locations);
        createScenes(scenes);
    }

    public void setTurnManager(TurnManager turnManager){
        this.turnManager = turnManager;
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
        view.updatePlayerInfo(player.getPlayerID(), player.getCash(), player.getCredits(), player.getRehearseChips(), player.getRank());
    }

    public String getPlayerCount(){
        return view.getPlayerCount();
    }

    public void initPlayers(int num_players, int starting_rank){
        view.buildPlayers(num_players, starting_rank);
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

    public void removeShotCounter(Set set){
        String shot = set.getName()+""+set.getNumShotCounters();
        view.removeShot(shot);
        //TODO are these zero indexed?
    }

    public void updatePlayerLocation(int ID, Location location){
        //Offsets the player by 10 pixels based on their ID to prevent overlap of player icons
        view.updatePlayerLocation(ID, location.getX() + (ID * 10), location.getY());
    }

    // move player icon to given role position, location is needed for relative coordinates
    public void updatePlayerMainRole(int ID, Role role, Location location){
        view.updatePlayerLocation(ID, location.getX() + role.getX(), location.getY() + role.getY());
    }

    // move player icon to given role position (extra roles only)
    public void updatePlayerExtraRole(int ID, Role role){
        view.updatePlayerLocation(ID, role.getX(), role.getY());
    }

    //Displays a popup menu
    public void displayMessage(String title, String message){
        view.displayMessage(title, message);
    }

    public void displayInConsole(String output){
        view.printOutput("output");
    }

    //Ends the game and scores the players
    public void endGame(){
        int result = JOptionPane.showConfirmDialog(view, "Are you sure?", "End Game", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            turnManager.endGameAction();
        }
    }
    public void moveAction(){
        //get list of valid locations
        //signal popup & wait for choice
        //updated turn is     
    }

    public void endTurn(){
        turnManager.endTurnAction();
    }

    //Displays the score and ends the game
    public void listWinners(ArrayList<Player> winners){
        StringBuilder sb = new StringBuilder();
        for(Player winner : winners){
            sb.append("Player " + winner.getPlayerID() + ": " + winner.getScore() + " points" + "\n");
        }
        displayMessage("Game Score", sb.toString());
    }
}