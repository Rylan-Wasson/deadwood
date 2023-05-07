import java.util.Scanner;

public class TextController {
    
    private TextView textView = new TextView();
    private Scanner sc; //TODO: SCANNER NOT CLOSED, INFINITE LOOP BUG?

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

    /*
     * Displays deadwood greeting and prompts user for player count
     */
    public int getPlayerCount(){
        boolean flag = false;
        textView.displayGreeting();
        textView.displayPlayerCount();
        int player_count = -1;

        while(!flag){
            textView.displayPlayerCount();
            player_count = readInt();

            if(player_count >= 2 && player_count <= 8){
                flag = true;
            } else {
                textView.invalidInput();
            }
        }
        return player_count;
    }

    public String getAction(){
        return null;
    }
}