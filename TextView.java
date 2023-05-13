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
        System.out.println("Invalid Selection!");
    }
}
