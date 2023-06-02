import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class boardMouseListener implements MouseListener{
    private BoardView view;

    
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()== view.getbAct()){
           System.out.println("Acting is Selected\n");
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
}
