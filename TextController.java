import java.util.Scanner;

public class TextController {
    private Scanner input_scanner = new Scanner(System.in);
    private TextView textView = new TextView();

    /*
     * Reads and returns a string from standard input
     */
    private String readStrings(){
        String input_string = "";
        try {
            input_string = input_scanner.nextLine();
        } catch (Exception e) {
            textView.invalidInput();
        }
        return input_string;
    }

    /*
     * Reads and returns an integer from standard input
     */
    private int readInt(){
        int input_int = -1;

        try {
            input_int = input_scanner.nextInt();
        } catch (Exception e) {
            textView.invalidInput();
        }
        return input_int;
    }

    /*
     * Displays deadwood greeting and prompts user for player count
     */
    public int getPlayerCount(){
        boolean flag = true;
        textView.displayGreeting();
        int player_count = -1;

        while(flag){
            textView.displayPlayerCount();
            player_count = readInt();
            if(player_count >= 2 && player_count <= 8){
                flag = false;
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