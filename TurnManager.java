import java.util.ArrayList;

public class TurnManager {
    private Boolean turn_active;
    private Boolean has_moved;
    private Player active_player;
    private TextController controller;
    private LocationManager lm;
    private boolean end_game;

    public TurnManager(TextController controller, LocationManager lm){
        this.controller = controller;
        this.lm = lm;
        this.active_player = null;
    }
    
    public boolean conductTurn(Player player){
        end_game = false;
        active_player = player;
        turn_active = true;
        has_moved = false;
        while(turn_active){
            //get action
            moveAction();
            endTurnAction();
        }
        return end_game;
    }

    private void moveAction(){
        Location cur_location = lm.getLocationByID(active_player.getPlayerID());
        ArrayList<String> adjacent_locations = cur_location.getAdjacentLocations();
        String selection = controller.getDesiredLocation(adjacent_locations);
        Location new_location = lm.getLocationByName(selection);

        if(new_location != null){
            lm.updateLocation(active_player.getPlayerID(), new_location);
            has_moved = true;
        } else {
            controller.badInput();
        }
        
    }

    private void takeRoleAction(){

    }

    private void actRoleAction(){

    }

    private void rehearseAction(){

    }

    private void upgradeAction(){

    }

    private void endTurnAction(){
        turn_active = false;
    }

    private void currentPlayerInfoAction(){

    }

    private void allPlayersInfoAction(){

    }

    private void endGameAction(){
        end_game = true;
    }

}
