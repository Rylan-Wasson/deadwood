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
                turn.hasMoved();
                lm.updateLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location)); // change location
                controller.updatePlayerLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location));
                gm.checkGameStatus();
            } else {
                controller.displayMessage("Invalid Action", "Already Moved");
            } 
        } else {
            controller.displayMessage("Invalid Action", "You're currently working a role!");
        }   
    }

    public void takeRoleAction(Role role){
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
                gm.checkGameStatus();
            } else { // insufficient rank
                controller.displayMessage("Invalid Action", "Your rank is too low!");
            }
        } else { // role already taken
            controller.displayMessage("Invalid Action", "That role is already taken!");
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
                set.decrementShotCounters();
                controller.
    
                if(active_player.getPlayerRole().getIsMainRole()){ //is a main role
                    banker.payPlayer(active_player, 2, "credits");
                }else{ // extra role
                    banker.payPlayer(active_player, 1, "credits");
                    banker.payPlayer(active_player, 1, "cash");
                }
            }
        } else {

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

    public void setTurn(Turn turn){
        this.turn = turn;
    }
}
