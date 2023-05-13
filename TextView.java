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

        System.out.println("\n Extra Roles: ");
        for(int i = 0; i < extra_roles.size(); i++){
            Role role = extra_roles.get(i);
            System.out.print("> "+role.getName());
            if(role.isTaken()){
                System.out.print("  (Taken)");
            }
            System.out.println();
            System.out.println(" Rank: "+role.getRank());
        }
    }

    public void listPlayerInfo(String name, String role, int cash, int credits, int rehearse_chips){
        System.out.println("Player: "+name);
        System.out.println("Current Role: "+role);
        System.out.println("Cash: "+cash);
        System.out.println("Credits: "+credits);
        System.out.println("Rehearse Chips: "+rehearse_chips);
    }
}
