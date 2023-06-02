public class Turn {
    private Player active_player;
    private boolean end_game;
    private boolean turn_active;
    private boolean has_moved;
    private boolean has_upgraded;

    public Turn(Player player){
        active_player = player;
        end_game = false;
        turn_active = true;
        has_moved = false;
        has_upgraded = false;
    }

    public void updateMoved(){
        has_moved = true;
    }

    public void updateUpgraded(){
        has_upgraded = true;
    }

    public Player getActivePlayer() {
        return active_player;
    }

    public Boolean gameOver(){
        return end_game;
    }

    public boolean turnActive(){
        return turn_active;
    }

    public boolean hasMoved(){
        return has_moved;
    }

    public boolean hasUpgraded(){
        return has_upgraded;
    }
}
