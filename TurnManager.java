import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TurnManager {
    private Banker banker;
    private GuiController controller;
    private LocationManager lm;
    private Turn turn;
    private GameManager gm;

    public TurnManager(GuiController controller, LocationManager lm, Turn turn, GameManager gm){
        banker = new Banker();
        this.controller = controller;
        this.lm = lm;
        this.turn = turn;
        this.gm = gm;
    }

    public void moveAction(String location){
        if(turn.getActivePlayer().getPlayerRole() == null){
            if(!turn.hasMoved()){
                turn.updateMoved();
                lm.updateLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location)); // change location
                controller.updatePlayerLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location));
                controller.removeCover(lm.getLocationByID(turn.getActivePlayer().getPlayerID()));
                gm.checkGameStatus();
            } else {
                controller.displayInConsole("Invalid Action: Already Moved");
            } 
        } else {
            controller.displayInConsole("Invalid Action: You're currently working a role!");
        }   
    }

    public void takeRoleAction(Role role){
        if(turn.getActivePlayer().getPlayerRole() == null){ // check that player is not already working
            if(!role.isTaken()){ //check that role is not already taken
                if(turn.getActivePlayer().getRank() >= role.getRank()){ //check that player has sufficient rank
                    turn.getActivePlayer().setPlayerRole(role);
                    role.setTaken(true);

                    // update player icon location
                    int ID = turn.getActivePlayer().getPlayerID();
                    if(role.getIsMainRole()){ // main role
                        controller.updatePlayerMainRole(ID, role, lm.getLocationByID(ID));
                    } else { // extra role
                        controller.updatePlayerExtraRole(ID, role);
                    }
                    turn.turnEnd();
                    gm.checkGameStatus();
                } else { // insufficient rank
                    controller.displayInConsole("Invalid Action: Your rank is too low!");
                }
            } else { // role already taken
                controller.displayInConsole("Invalid Action: That role is already taken!");
            }
        } else { // already working
            controller.displayInConsole("Invalid Action: You're already working a role!");
        }
    }

    public void actRoleAction(){
        Player active_player = turn.getActivePlayer();
        if(active_player.getPlayerRole() != null){ // check that player has a role
            
            Random player_roll = new Random();
            int roll_result = (player_roll.nextInt(6) + 1);
            Set set = (Set) lm.getLocationByID(turn.getActivePlayer().getPlayerID());
            int budget = set.getScene().getBudget();
            
            if((roll_result + active_player.getRehearseChips()) >= budget){ //successful roll
                controller.removeShotCounter(set); // hide the shot counter
                set.decrementShotCounters();
    
                if(active_player.getPlayerRole().getIsMainRole()){ //is a main role
                    banker.payPlayer(active_player, 2, "credits"); 
                    controller.displayInConsole("Success! you earned 2 credits");
                }else{ // extra role
                    banker.payPlayer(active_player, 1, "credits");
                    banker.payPlayer(active_player, 1, "cash");
                    controller.displayInConsole("Success! you earned 1 credit and 1 dollar!");
                }

                if(set.getNumShotCounters() == 0){ //bonus check
                    ArrayList<Player> players_by_location = lm.getPlayersByLocation(set);
                    ArrayList<Player> main_role_players = new ArrayList<Player>();
                    ArrayList<Player> extra_role_players = new ArrayList<Player>();
                    
                    for(int i = 0; i < players_by_location.size(); i++){ //adding the players into 2 groups
                        if(players_by_location.get(i).getPlayerRole() != null){
                            if(players_by_location.get(i).getPlayerRole().getIsMainRole()){
                                main_role_players.add(players_by_location.get(i));
                            } else {
                                extra_role_players.add(players_by_location.get(i));
                            }
                        }
                    }
                    //Sorts the players who have a main role by the rank of said role
                    Collections.sort(main_role_players, (p1, p2) -> p1.getPlayerRole().getRank() - p2.getPlayerRole().getRank());
                    // distribute bonuses
                    if(main_role_players.size() > 0){
                        banker.sceneBonus(main_role_players, budget);
                    }

                    if(extra_role_players.size() > 0){
                        banker.extraBonus(extra_role_players);
                    }
                    controller.displayInConsole("Bonuses distributed!");
                    
                    // reset set/player info related to scene
                    for(int i = 0; i < players_by_location.size(); i++){
                        players_by_location.get(i).resetRoleStatus();
                    }

                    controller.removeCard(set.getScene());
                    set.setScene(null);
                    lm.decrementNumScenes();
                    
                }
                
            } else {
                controller.displayInConsole("Roll Failed!");
            }

            turn.turnEnd();
            gm.checkGameStatus();
        } else {
            controller.displayInConsole("Invalid action: You're not currently working a role!");
        }
    }

    /* Give the player a rehearse chip */
    public void rehearseAction(){
        Player active_player = turn.getActivePlayer();
        if(active_player.getPlayerRole()!=null){ // check that player is working a role
            banker.payPlayer(active_player, 1, "rehearse_chips");
            controller.displayInConsole("Chip recieved!");
            controller.updatePlayerInfo(active_player);
            turn.turnEnd();
            gm.checkGameStatus();
        } else {
            controller.displayInConsole("Invalid action: Not currently working a role!");
        }
    }

    public void endTurnAction(){
        turn.turnEnd();
        gm.checkGameStatus();
    }

    public void endGameAction(){
        turn.endGame();
        gm.checkGameStatus();
    }

    public Turn getTurn(){
        return turn;
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }

    // get active players adjacent locations
    public ArrayList<String> getAdjacentLocations(){
        int ID = turn.getActivePlayer().getPlayerID();
        ArrayList<String> adj_locations = lm.getLocationByID(ID).getAdjacentLocations();
        return adj_locations;
    }

    // get roles available at active players postion
    public ArrayList<Role> getAvailableRoles(){
        Player player = turn.getActivePlayer();
        Location location = lm.getLocationByID(player.getPlayerID());
        if(location instanceof Set){ // check if location is a set
            Set set = (Set) location;
            if(set.getScene() != null){ // check for an active scene
                ArrayList<Role> main = set.getScene().getRoles();
                ArrayList<Role> extra = set.getExtraRoles();
                ArrayList<Role> ret_list = new ArrayList<Role>();
                for(int i = 0; i < main.size(); i++){
                    ret_list.add(main.get(i));
                }

                for(int i = 0; i < extra.size(); i++){
                    ret_list.add(extra.get(i));
                }   
                return ret_list;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void upgradeAction(Upgrade upgrade){
        System.out.println("----> AYAYAYAYA");
    }

    public ArrayList<Upgrade> getUpgrades(){
        if(lm.getLocationByID(turn.getActivePlayer().getPlayerID()) == lm.getLocationByName("office")){
            CastingOffice office = (CastingOffice) lm.getLocationByName("office");
            return office.getUpgrades();
        } else {
            return null;
        }
    }
}
