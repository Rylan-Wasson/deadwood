import java.util.ArrayList;

public class TurnManager {
    private Boolean turn_active;
    private Boolean has_moved;
    private Player active_player;
    private TextController controller;
    private LocationManager lm;

    public TurnManager(TextController controller){
        
    }
    
    public void conductTurn(Player player){
        turn_active = true;
        while(turn_active){
            //get action

        }
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

    }

    private void currentPlayerInfoAction(){

    }

    private void allPlayersInfoAction(){

    }

    private void endGameAction(){
        
    }

}
