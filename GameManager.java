import java.util.LinkedList;
import java.util.Random;

public class GameManager {
    private int num_days;
    private int num_players;
    private LinkedList<Player> players = new LinkedList<>();
    private final int dice_sides = 6;

    //Getters
    public int getNumDays(){
        return this.num_days;
    }

    public int getNumPlayers(){
        return this.num_players;
    }

    public LinkedList<Player> getPlayers(){
        return this.players;
    }

    //Setters
    public void setNumDays(int num_days){
        this.num_days = num_days;
    }

    public void setNumPlayers(int num_players){
        this.num_players = num_players;
    }

    //Util functions
    private void playDay(){

    }

    /*
     * Sets up the game, utilizes the TextController to recieve input
     */
    public void setupGame(){
        TextController textController = new TextController();
        setNumPlayers(textController.getPlayerCount());

        // Creates all the players based on player count, adds them to the players LinkedList
        for(int i = 1; i <= num_players; i++){
            Player new_player = null;
            switch(num_players){
                case 5:
                    new_player = new Player(i, 1, 0, 0, 2, "Trailers");
                    break;
                case 6:
                    new_player = new Player(i, 1, 0, 0, 4, "Trailers");
                    break;
                case 7:
                    new_player = new Player(i, 2, 0, 0, 0, "Trailers");
                    break;
                case 8:
                    new_player = new Player(i, 2, 0, 0, 0, "Trailers");
                    break;
                default:
                    new_player = new Player(i, 1, 0, 0, 0, "Trailers");
                    break;
            }
            this.players.add(new_player);
        }

        //Sets the day cap based on player count
        switch(num_players){
            case 2:
                setNumDays(3);
                break;
            case 3:
                setNumDays(3);
                break;
            default:
                setNumDays(4);
                break;
        }

        //tests
        System.out.println("PlayerCount: " + num_players);
        System.out.println("Days: " + num_days);
        for(int i = 0; i < num_players; i++){
            Player curPlayer = players.get(i);
            System.out.println("id: " + curPlayer.getPlayer_id());
            System.out.println("rank: " + curPlayer.getRank());
            System.out.println("location: " + curPlayer.getPlayer_location_name());
            System.out.println("credits: " + curPlayer.getCredits());
            System.out.println("cash: " + curPlayer.getCash());
            System.out.println("chips: " + curPlayer.getRehearse_chips());
            System.out.println("--------------------------------");
        }
    }
    
    /*
     * Rolls X dice and returns an int[] of the generate rolls 
     */
    public int[] rollXDice(int num_dice){
        Random randomInt = new Random();
        int[] dice_outcomes = new int[num_dice];
        //upper bound is exclusive, offset is added to accurately represent a dice roll
        int offset = 1;

        for(int i = 0; i < num_dice; i++){
            dice_outcomes[i] = (randomInt.nextInt(dice_sides) + offset);
        }
        return dice_outcomes;
    }
}
