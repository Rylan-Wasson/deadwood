public class TurnManager {
    private Banker banker;
    private GuiController controller;
    private LocationManager lm;
    private Turn turn;
    private GameManager gm;

    public TurnManager(GuiController controller, LocationManager lm, Turn turn, GameManager gm){
        banker = new Banker();
        this.controller = controller;
        this.lm = lm;
        this.turn = turn;
        this.gm = gm;
    }

    public void moveAction(String location){
        if(turn.getActivePlayer().getPlayerRole() == null){
            if(!turn.hasMoved()){
                lm.updateLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location)); // change location
                controller.updatePlayerLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location));
                gm.checkGameStatus();
            } else {
                controller.displayMessage("Invalid Action", "Already Moved");
            }
        }    
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }
}
