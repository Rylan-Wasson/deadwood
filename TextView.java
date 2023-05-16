import java.util.ArrayList;

public class TextView {
    public void invalidInput(){
        System.out.println("Invalid Input! Please try again.");
    }

    public void listLocations(ArrayList<String> locations){
        System.out.println("Enter a location:");
        for(int i = 0; i < locations.size(); i++){
            System.out.println("> " + locations.get(i));
        }
    }

    public void printBadSelection(){
        System.out.println("Invalid Selection! Please try again.");
    }

    public void printBadSelectionWorkingRole(){
        System.out.println("Cannot do this action, you are currently working a role. Please try again.");
    }

    public void printBadSelectionWrongLocation(){
        System.out.println("Cannot do this action, you are not at the right location. Please try again.");
    }

    public void printBadSelectionAlreadyMoved(){
        System.out.println("Cannot do this action, you've already moved. Please try again.");
    }

    public void printBadSelectionHasUpgraded(){
        System.out.println("Cannot do this action, you've already upgraded. Please try again.");
    }

    public void printBadSelectionNoRole(){
        System.out.println("Cannot do this action, you're not currently working a role. Please try again.");
    }

    public void printBadSelectionNoScene(){
        System.out.println("Cannot do this action, there is no scene available. Please try again.");
    }

    public void printBadSelectionRoleTaken(){
        System.out.println("Cannot do this action, that role is already taken. Please try again.");
    }

    public void printBadSelectionLowRank(){
        System.out.println("Cannot do this action, your rank is too low. Please try again.");
    }

    public void printBadSelectionInvalidFunds(){
        System.out.println("Cannot do this action, you don't have sufficent funds. Please try again.");
    }



    public void listRoles(ArrayList<Role> main_roles, ArrayList<Role> extra_roles){
        System.out.println("Select a role: ");
        System.out.println("\n Scene Roles:");
        for(int i = 0; i < main_roles.size(); i++){
            Role role = main_roles.get(i);
            System.out.print("> "+role.getName());
            if(role.isTaken()){
                System.out.print("  (Taken)");
            }
            System.out.println();
            System.out.println(" Rank: "+role.getRank());
        }

        System.out.println("\n  Extra Roles: ");
        for(int i = 0; i < extra_roles.size(); i++){
            Role role = extra_roles.get(i);
            System.out.print("> "+role.getName());
            if(role.isTaken()){
                System.out.print("  (Taken)");
            }
            System.out.println();
            System.out.println("   Rank: "+role.getRank());
        }
    }

    public void listPlayerInfo(int ID, int rank, String role, int cash, int credits, int rehearse_chips){
        System.out.println("\nInfo:");
        System.out.println("Player: "+ID);
        System.out.println("Rank: "+rank);
        System.out.println("Current Role: "+role);
        System.out.println("Cash: "+cash);
        System.out.println("Credits: "+credits);
        System.out.println("Rehearse Chips: "+rehearse_chips);
    }

    public void listActions(){
        System.out.println("\nActions:");
        System.out.println(" -> player info\n -> all player info\n -> move\n -> act\n -> upgrade\n -> rehearse\n -> take role\n -> end turn\n -> end game\n------------");
    }

    public void listUpgrades(ArrayList<Upgrade> upgrades){
        System.out.println("Select a level (type number): ");
        for(int i = 0; i < upgrades.size(); i++){
            Upgrade upgrade = upgrades.get(i);
            System.out.println("> Level ("+upgrade.getLevel()+") Cost: "+upgrade.getAmmount()+" "+upgrade.getCurrency());
        }
    }

    public void listWinners(ArrayList<Player> winners){
        System.out.println("\n---Winners are---");
        for(int i = 0; i < winners.size(); i++){
            Player cur = winners.get(i);
            System.out.println("Player "+cur.getPlayerID()+" Score: "+cur.getScore());
        }
    }

    public void newDay(int ID, int days){
        System.out.println("Player "+ID+"'s turn. "+days+" days remain!");
    }

    public void promptCurrency(){
        System.out.println("What currency would you like to use? (dollar or credit)");
    }
}
