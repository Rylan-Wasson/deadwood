import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Banker {
    /* adds amount of type currency to player */
    public void payPlayer(Player player, int amount, String currency) {
        if(currency.equals("credits")){
            player.setCredits(player.getCredits() + amount);
        } else if(currency.equals("cash")){
            player.setCash(player.getCash() + amount);
        } else if(currency.equals("rehearse_chips")){
            player.setRehearse_chips(player.getRehearseChips() + amount);
        }
    }


    /*  remove amount of type currency to given player object. verifies player has enough of given currency.
     *  returns 0 on success, -1 on failure
    */
    public int removeFunds(Player player, int amount, String currency) {
        if(verifyFunds(player, amount, currency)){
            if(currency.equals("credit")){
                player.setCredits(player.getCredits() - amount);
            } else if(currency.equals("dollar")){
                player.setCash(player.getCash() - amount);
            } else if(currency.equals("rehearse_chips")){
                player.setRehearse_chips(player.getRehearseChips() - amount);
            }
            return 0;
        } else {
            return -1;
        }
            
    }
    /*  verifies that player has ammount of type currency or more */
    private boolean verifyFunds(Player player, int amount, String currency){
        if(currency.equals("credit")){
            if(player.getCredits() >= amount){
                return true;
            }
        } else if(currency.equals("dollar")){
            if(player.getCash() >= amount){
                return true;
            }
        } else if(currency.equals("rehearse_chips")){
            if(player.getRehearseChips() > amount){
                return true;
            }
        }
        
        return false;
    }

    
    /* Distribute budget d6 rolls worth of cash for the scene bonus,
     * assumes actors is sorted by role level in ascending order
     * also assumes actors is not null
     */
    public void sceneBonus(ArrayList<Player> actors, int budget) {
        int[] rolls = rollXDice(budget);
        int j = actors.size()-1; // actors index
        Arrays.sort(rolls);

        /* Iterate through dice rolls in descending order */
        for(int i = rolls.length-1; i >= 0; i--){
            if(j < 0){
                j = actors.size()-1;
            }
            payPlayer(actors.get(j), rolls[i], "cash");
            j--;
        }
    }
    /*  Distribute a cash bonus to each actors equal to
     *  the level of their currently worked role
     */
    public void extraBonus(ArrayList<Player> actors){
        for(int i = 0; i < actors.size(); i++){
            Player actor = actors.get(i);
            int bonus = actor.getPlayerRole().getRank();
            payPlayer(actor, bonus, "cash");
        }
    }

    /*
     * Rolls X dice and returns an int[] of the generate rolls 
     */
    public int[] rollXDice(int num_dice){
        Random randomInt = new Random();
        int[] dice_outcomes = new int[num_dice];
        
        for(int i = 0; i < num_dice; i++){
            dice_outcomes[i] = (randomInt.nextInt(6) + 1); //exclusive upper bound
        }
        return dice_outcomes;
    }
}
