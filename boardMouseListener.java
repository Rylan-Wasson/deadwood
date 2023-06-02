import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class boardMouseListener implements MouseListener{
    private BoardView view;
    private String action = null;
    private GuiController controller;

    public boardMouseListener(GuiController controller){
        this.controller = controller;
    }
    
    public void mouseClicked(MouseEvent e) {

        //Action button events
        if (e.getSource()== view.getbAct()){
            view.printOutput("Act Selected");

        } else if(e.getSource() == view.getbMove()){
            view.printOutput("Move Selected");

        } else if(e.getSource() == view.getbRehearse()){
            view.printOutput("Rehearse Selected");

        } else if(e.getSource() == view.getbTake()){
            view.printOutput("Take role Selected");

        } else if(e.getSource() == view.getbUpgrade()){
            view.printOutput("Upgrade Selected");

        } else if(e.getSource() == view.getbEndGame()){
            view.printOutput("End game Selected");
            controller.endGame();

        } else if(e.getSource() == view.getbEndTurn()){
            view.printOutput("End turn Selected");

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
