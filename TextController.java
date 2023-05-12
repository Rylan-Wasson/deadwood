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
    private int readInt(){
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
        return null;
    }
}