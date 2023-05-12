import java.util.LinkedList;

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
            if(currency.equals("credits")){
                player.setCredits(player.getCredits() - amount);
            } else if(currency.equals("cash")){
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
        if(currency.equals("credits")){
            if(player.getCredits() >= amount){
                return true;
            }
        } else if(currency.equals("cash")){
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

    

    private void sceneBonus(LinkedList<Player> actors, int budget) {

    }
}
