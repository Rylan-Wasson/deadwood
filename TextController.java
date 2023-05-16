import java.util.ArrayList;
import java.util.Scanner;

public class TextController {
    
    private TextView textView;
    private Scanner sc; //TODO: SCANNER NOT CLOSED, INFINITE LOOP BUG?

    public TextController(){
        textView = new TextView();
    }

    /*
     * Reads and returns a string from standard input
     */
    private String readStrings(){
        try {
            sc = new Scanner(System.in);
            String input;
            input = sc.nextLine();
            return input;
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * Reads and returns an integer from standard input
     */
    public int readInt(){
        try {
            sc = new Scanner(System.in);
            int input;
            input = sc.nextInt();
            return input;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getDesiredLocation(ArrayList<String> locations){
        textView.listLocations(locations);
        String selection = readStrings();
        
        return selection;
    }

    public String getAction(){
        return readStrings();
    }

    public String getRoleSelection(){
        return readStrings();
    }

    public void badInput(){
        textView.printBadSelection();
    }

    public void badInput(String arg){
        switch (arg) {
            case "working_role":
                textView.printBadSelectionWorkingRole();
                break;
            case "wrong_location":
                textView.printBadSelectionWrongLocation();
                break;
            case "already_moved":
                textView.printBadSelectionAlreadyMoved();
                break;
            case "has_moved":
                textView.printBadSelectionHasUpgraded();
                break;
            case "no_role":
                textView.printBadSelectionNoRole();
                break;
            case "no_scene":
                textView.printBadSelectionNoScene();
                break;
            case "role_taken":
                textView.printBadSelectionRoleTaken();
                break;
            case "low_rank":
                textView.printBadSelectionLowRank();
                break;
            default:
                break;
        }
    }

    public void playerInfo(Player player){
        int ID = player.getPlayerID();
        int rank = player.getRank();
        String role;
        if(player.getPlayerRole() != null){
            role = player.getPlayerRole().getName();
        } else {
            role = "No role";
        }
        int cash = player.getCash();
        int credits = player.getCredits();
        int rehearse_chips = player.getRehearseChips();
        textView.listPlayerInfo(ID, rank, role, cash, credits, rehearse_chips);
    }

    public void allPlayerInfo(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++){
            playerInfo(players.get(i));
        }
    }

    public void listRoles(ArrayList<Role> main_roles, ArrayList<Role> extra_roles){
        textView.listRoles(main_roles, extra_roles);
    }

    public void listUpgrades(ArrayList<Upgrade> upgrades){
        textView.listUpgrades(upgrades);
    }

    public void listActions(){
        textView.listActions();
    }

    public void promptCurrency(){
        textView.promptCurrency();
    }

    public void closeScanner(){
        sc.close();
    }
}