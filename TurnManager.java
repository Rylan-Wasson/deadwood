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
            String action = controller.getAction();
            switch (action) {
                case "end turn":
                    endTurnAction();
                    break;
                case "end game": //ends the current turn and the game
                    endTurnAction();
                    endGameAction();
                    break;    
                case "move":
                    if(active_player.getPlayerRole() == null){
                        if(!has_moved){
                            moveAction();
                        } else {
                            controller.badInput("already_moved");
                        }
                    } else {
                        controller.badInput("working_role");
                    }
                    break;
                case "act":
                    if(player.getPlayerRole() != null){
                        actRoleAction();
                    } else {
                        controller.badInput("no_role");
                    }
                    break;
                case "active player info":
                    currentPlayerInfoAction();
                    break;
                case "all player info":
                    allPlayersInfoAction();
                    break;
                case "upgrade":
                if(active_player.getPlayerRole() == null){
                    if(lm.getLocationByID(active_player.getPlayerID()).getName().equalsIgnoreCase("office")){
                        upgradeAction();
                    } else {
                        controller.badInput("wrong_location");
                    }  
                } else {
                    controller.badInput("working_role");
                }
                    break;
                case "rehearse":
                if(player.getPlayerRole() != null){
                    rehearseAction();
                } else {
                    controller.badInput("no_role");
                }
                    break;
                case "take role":
                if(active_player.getPlayerRole() == null){ // check that player isnt already working a role
                    if((lm.getLocationByID(active_player.getPlayerID())) instanceof Set){ // check that location is a set
                        Set set = (Set) lm.getLocationByID(active_player.getPlayerID());
                        if(set.getScene() != null){ // check that there is an active scene
                            takeRoleAction();
                        } else {
                            controller.badInput("no_scene");
                        }
                    } else {
                        controller.badInput("no_scene");
                    }
                } else {
                    controller.badInput("working_role");
                }
                    
                    break;

                default:
                    controller.badInput();
                    break;
            }
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
        Set set = (Set) lm.getLocationByID(active_player.getPlayerID());
        ArrayList<Role> main_roles = set.getScene().getRoles();
        ArrayList<Role> extra_roles = set.getExtraRoles();
        controller.listRoles(main_roles, extra_roles);
        
        String role_selection = controller.getRoleSelection();
        Role role = null;
        // find if player selection is a valid role
        for(int i = 0; i < main_roles.size(); i++){
            if(main_roles.get(i).getName().equalsIgnoreCase(role_selection)){
                role = main_roles.get(i);
            }
        }
        for(int i = 0; i < extra_roles.size(); i++){
            if(extra_roles.get(i).getName().equalsIgnoreCase(role_selection)){
                role = extra_roles.get(i);
            }
        }

        if(role != null){ //check that role is valid
            if(!role.taken){ //check that role is not taken
                if(active_player.getRank() >= role.getRank()){ //check that player has sufficient rank
                    active_player.setPlayerRole(role);
                    role.setTaken(true);
                    turn_active = false;
                } else {
                    controller.badInput("low_rank");
                }
            } else {
                controller.badInput("role_taken");
            }
        } else {
            controller.badInput();
        }
    }

    private void actRoleAction(){
        System.out.println("--act");
    }

    private void rehearseAction(){
        System.out.println("--rehearse");
    }

    private void upgradeAction(){
        System.out.println("--upgrade");
    }

    private void endTurnAction(){
        System.out.println("--endturn");
        turn_active = false;
    }

    private void currentPlayerInfoAction(){
        System.out.println("--player info");
        controller.playerInfo(active_player);
    }

    private void allPlayersInfoAction(){
        System.out.println("-- all info");
    }

    private void endGameAction(){
        System.out.println("-- end game");
        end_game = true;
    }

}
