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
                turn.hasMoved();
                lm.updateLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location)); // change location
                controller.updatePlayerLocation(turn.getActivePlayer().getPlayerID(), lm.getLocationByName(location));
                gm.checkGameStatus();
            } else {
                controller.displayMessage("Invalid Action", "Already Moved");
            } 
        } else {
            controller.displayMessage("Invalid Action", "You're currently working a role!");
        }   
    }

    public void endTurnAction(){
        turn.turnEnd();
        gm.checkGameStatus();
    }

    public void endGameAction(){
        turn.endGame();
        gm.checkGameStatus();
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }
}
