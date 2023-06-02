public class TurnManager {
    private Banker banker;
    private GuiController controller;
    private LocationManager lm;
    private Turn turn;

    public TurnManager(GuiController controller, LocationManager lm, Turn turn){
        banker = new Banker();
        this.controller = controller;
        this.lm = lm;
        this.turn = turn;
    }

    public void moveAction(String location){
        if(turn.getActivePlayer().getPlayerRole() == null){
            if(!turn.hasMoved()){
                lm.updateLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location)); // change location
                controller.updatePlayerLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location));
            } else {
                controller.displayMessage("Invalid Action", "Already Moved");
            }
        }    
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }
}
