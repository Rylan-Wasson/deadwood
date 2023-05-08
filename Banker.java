import java.util.LinkedList;

public class Banker {
    public void payPlayer(Player player, int amount, String currency) {
        if(currency.equals("credits")){
            player.setCredits(player.getCredits() + amount);
        } else if(currency.equals("cash")){
            player.setCash(player.getCash() + amount);
        } else if(currency.equals("rehearse_chips")){
            player.setRehearse_chips(player.getRehearse_chips() + amount);
        }
    }

    private boolean verifyFunds(Player player, int amount, String currency){
        if(currency.equals("credits")){
            if(player.getCredits() > amount){
                return true;
            }
        } else if(currency.equals("cash")){
            if(player.getCash() > amount){
                return true;
            }
        } else if(currency.equals("rehearse_chips")){
            if(player.getRehearse_chips() > amount){
                return true;
            }
        }
        
        return false;
    }

    public void removeFunds(Player player, int amount, String currency) {
        if(currency.equals("credits")){
            player.setCredits(player.getCredits() - amount);
        } else if(currency.equals("cash")){
            player.setCash(player.getCash() - amount);
        } else if(currency.equals("rehearse_chips")){
            player.setRehearse_chips(player.getRehearse_chips() - amount);
        }
    }

    private void sceneBonus(LinkedList<Player> actors, int budget) {

    }
}
