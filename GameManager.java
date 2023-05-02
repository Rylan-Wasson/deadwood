import java.util.LinkedList;

public class GameManager {
    private int num_days;
    private int num_players;
    private LinkedList<Player> players;

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
    
    private int[] rollXDice(){
        return null;
    }
}
