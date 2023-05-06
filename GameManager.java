import java.util.LinkedList;
import java.util.Random;

public class GameManager {
    private int num_days;
    private int num_players;
    private LinkedList<Player> players;
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
