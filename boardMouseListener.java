import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class boardMouseListener implements MouseListener{
    private BoardView view;
    private GuiController controller;
    private String action;

    public boardMouseListener(GuiController controller){
        this.controller = controller;
    }

    public void mouseClicked(MouseEvent e) {

        //Action button events
        if (e.getSource()== view.getbAct()){
            controller.actAction();

        } else if(e.getSource() == view.getbMove()){
            controller.moveAction();

        } else if(e.getSource() == view.getbRehearse()){

        } else if(e.getSource() == view.getbTake()){
            controller.takeRoleAction();

        } else if(e.getSource() == view.getbUpgrade()){
            view.printOutput("Upgrade Selected");

        } else if(e.getSource() == view.getbEndGame()){
            controller.endGame();

        } else if(e.getSource() == view.getbEndTurn()){
            controller.endTurn();

        }
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    public void setView(BoardView view){
        this.view = view;
    }

    public String getAction(){
        return this.action;
    }

    public void setAction(String action){
        this.action = action;
    }
}
