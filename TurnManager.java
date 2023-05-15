import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class TurnManager {
    private Boolean turn_active;
    private Boolean has_moved;
    private Player active_player;
    private TextController controller;
    private LocationManager lm;
    private boolean end_game;
    private Banker banker;
    private int num_actions;
    private ArrayList<Player> players;

    public TurnManager(TextController controller, LocationManager lm, ArrayList<Player> players){
        this.controller = controller;
        this.lm = lm;
        this.active_player = null;
        this.players = players;
        banker = new Banker();
    }
    
    public boolean conductTurn(Player player){
        end_game = false;
        active_player = player;
        turn_active = true;
        has_moved = false;
        num_actions = 0;
        while(turn_active && !end_game && num_actions < 2){
            controller.listActions();
            String action = controller.getAction();
            switch (action) {
                case "end turn":
                    endTurnAction();
                    break;
                case "end game": //ends the current turn and the game
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
                case "player info":
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
        Boolean valid_location = false;
        
        if(new_location != null){
            for (int i = 0; i < adjacent_locations.size(); i++) { //ensures the desired location is adjacent
                if(adjacent_locations.get(i).equalsIgnoreCase(selection)){
                    valid_location = true;
                }
            }
            if(valid_location){ 
                lm.updateLocation(active_player.getPlayerID(), new_location);
                has_moved = true;
                num_actions++;
            } else {
                controller.badInput();
            }
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
                    num_actions++;
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
        Random player_roll = new Random();
        int roll_result = (player_roll.nextInt(6) + 1);
        Set set = (Set) lm.getLocationByID(active_player.getPlayerID());
        int budget = set.getScene().getBudget();

        if((roll_result + active_player.getRehearseChips()) >= budget){ //successful roll

            System.out.println("\nSuccessful roll!");
            set.setShotCounters(set.getShotCounters() - 1);

            if(active_player.getPlayerRole().getIsMainRole()){ //is a main role
                banker.payPlayer(active_player, 2, "credits");
                num_actions++;
            }else{
                banker.payPlayer(active_player, 1, "credits");
                banker.payPlayer(active_player, 1, "cash");
                num_actions++;
            }

            if(set.getShotCounters() == 0){ //bonus check
                ArrayList<Player> players_by_location = lm.getPlayersByLocation(set);
                ArrayList<Player> main_role_players = new ArrayList<Player>();
                ArrayList<Player> extra_role_players = new ArrayList<Player>();
                
                for(int i = 0; i < players_by_location.size(); i++){ //adding the players into 2 groups
                    if(players_by_location.get(i).getPlayerRole().getIsMainRole()){
                        main_role_players.add(players_by_location.get(i));
                    } else {
                        extra_role_players.add(players_by_location.get(i));
                    }
                }
                //TODO: test!
                Collections.sort(main_role_players, (p1, p2) -> p1.getPlayerRole().getRank() - p2.getPlayerRole().getRank());

                // distribute bonuses
                if(main_role_players.size() > 0){
                    banker.sceneBonus(main_role_players, budget);
                }

                if(extra_role_players.size() > 0){
                    banker.extraBonus(extra_role_players);
                }
                
                // reset set/player info related to scene
                for(int i = 0; i < players_by_location.size(); i++){
                    players_by_location.get(i).resetRoleStatus();
                }
                set.setScene(null);
                
            }
        } else { //unsuccessful roll
            System.out.println("\nYou're a fucking failure Eli");
        }
        turn_active = false;
    }

    private void rehearseAction(){
        banker.payPlayer(active_player, 1, "rehearse_chips");
        turn_active = false;
    }

    private void upgradeAction(){
        CastingOffice office = (CastingOffice) lm.getLocationByName("office");
        ArrayList<Upgrade> upgrades = office.getUpgrades();
        controller.listUpgrades(upgrades);  
        int level_selection = controller.readInt();
        
        controller.promptCurrency();
        String currency_selection = controller.getAction();

        Upgrade upgrade = null;
        // find matching upgrade to players selection if it exists
        for(int i = 0; i < upgrades.size(); i++){
            Upgrade cur = upgrades.get(i);
            if(cur.getLevel() == level_selection && cur.getCurrency().equalsIgnoreCase(currency_selection)){
                upgrade = cur;
            }
        }

        // if upgrade exists, verify and complete transaction
        if(upgrade != null && upgrade.getLevel() > active_player.getRank()){
            banker.removeFunds(active_player, upgrade.getAmmount(), upgrade.getCurrency());
            num_actions++;
        } else {
            controller.badInput();
        }
    }

    private void endTurnAction(){
        turn_active = false;
    }

    private void currentPlayerInfoAction(){
        controller.playerInfo(active_player);
    }

    private void allPlayersInfoAction(){
        controller.allPlayerInfo(players);
    }

    private void endGameAction(){
        end_game = true;
    }

}
