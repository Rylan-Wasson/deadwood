import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardView extends JFrame{
    final String COVER_IMG = "./Images/CardBack-small.jpg";
    final String SHOT_IMG = "./Images/shot.png";
    final char[] COLORS = {'b', 'c', 'g', 'o', 'p', 'r', 'v', 'w', 'y'};
    HashMap<String, JLabel> cards;
    HashMap<String, JLabel> covers;
    HashMap<Integer, JLabel> players;
    HashMap<String, JLabel> shots;
    // JLabels
    JLabel boardlabel;
    

    JLabel mLabel;
    JLabel infoLabel;
  
    //JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bTake;
    JButton bUpgrade;
    JButton bEndTurn;
    JButton bEndGame;

    // JLayered Pane
    JLayeredPane bPane;

    // Misc
    JTextArea tArea;
    boardMouseListener listener;

    public BoardView(boardMouseListener listener){
        super("Deadwood");
        this.listener = listener;
        cards = new HashMap<>();
        covers = new HashMap<>();
        players = new HashMap<>();
        shots = new HashMap<>();
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLayeredPane to hold the display, cards, dice and buttons
        bPane = getLayeredPane();

        // Create the deadwood board
        boardlabel = new JLabel();
        ImageIcon icon =  new ImageIcon("./Images/board.jpg");
        boardlabel.setIcon(icon); 
        boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        // Add the board to the lowest layer
        bPane.add(boardlabel, (Integer) 0);

        // Set the size of the GUI
        setSize(icon.getIconWidth()+300,icon.getIconHeight()+200);

        // Add a scene card to this room
        // cardlabel = new JLabel();
        // ImageIcon cIcon =  new ImageIcon("./Images/01.png");
        // cardlabel.setIcon(cIcon); 
        // cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
        // System.out.println(cIcon.getIconHeight());
        // cardlabel.setOpaque(true);

        // Add the card to the lower layer
        // bPane.add(cardlabel, (Integer) 1);




        // Add a dice to represent a player. 
        // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
        // playerlabel = new JLabel();
        // ImageIcon pIcon = new ImageIcon("./Images/r2.png");
        // playerlabel.setIcon(pIcon);
        // //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
        // playerlabel.setBounds(114,227,46,46);
        // playerlabel.setVisible(true);
        // bPane.add(playerlabel,(Integer) 3);

        // Create the Menu for action buttons

        mLabel = new JLabel("Actions");
        mLabel.setBounds(icon.getIconWidth()+65,0,100,20);
        bPane.add(mLabel,(Integer) 2);

        // Create Action buttons
        bAct = new JButton("ACT");
        bAct.setBackground(Color.white);
        bAct.setBounds(icon.getIconWidth()+30, 30,120, 20);
        bAct.addMouseListener(listener);

        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.white);
        bRehearse.setBounds(icon.getIconWidth()+30,60,120, 20);
        //bRehearse.addMouseListener(new boardMouseListener());

        bMove = new JButton("MOVE");
        bMove.setBackground(Color.white);
        bMove.setBounds(icon.getIconWidth()+30,90,120, 20);
        //bMove.addMouseListener(new boardMouseListener());

        bTake = new JButton("TAKE ROLE");
        bTake.setBackground(Color.white);
        bTake.setBounds(icon.getIconWidth()+30,120,120, 20);
        //bMove.addMouseListener(new boardMouseListener());

        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.white);
        bUpgrade.setBounds(icon.getIconWidth()+30,150,120, 20);
        //bMove.addMouseListener(new boardMouseListener());

        bEndTurn = new JButton("END TURN");
        bEndTurn.setBackground(Color.white);
        bEndTurn.setBounds(icon.getIconWidth()+30,180,120, 20);
        //bMove.addMouseListener(new boardMouseListener());

        bEndGame = new JButton("END GAME");
        bEndGame.setBackground(Color.white);
        bEndGame.setBounds(icon.getIconWidth()+30,210,120, 20);
        //bMove.addMouseListener(new boardMouseListener());


        //player info label
        infoLabel = new JLabel("Current Player:          Money:           Credits:              Rehearse Tokens:                ");
        infoLabel.setBounds(icon.getIconWidth()/4,icon.getIconHeight()+25,600,50);
        infoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        bPane.add(infoLabel, (Integer) 2);

        //info text box
        tArea = new JTextArea();
        tArea.setBounds(icon.getIconWidth() + 25, icon.getIconHeight() - 250, 250, 250);
        tArea.setEditable(false);
        tArea.setBorder(BorderFactory.createLineBorder(Color.black));
        bPane.add(tArea, (Integer) 2);

        // scroll pane for text box
        JScrollPane scroll_pane = new JScrollPane(tArea);
        scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_pane.setBounds(icon.getIconWidth() + 25, icon.getIconHeight() - 250, 250, 250);
        bPane.add(scroll_pane, (Integer) 2);

        // Place the action buttons in the top layer
        bPane.add(bAct, (Integer) 2);
        bPane.add(bRehearse, (Integer) 2);
        bPane.add(bMove, (Integer) 2);
        bPane.add(bTake, (Integer) 2);
        bPane.add(bUpgrade, (Integer) 2);
        bPane.add(bEndTurn, (Integer) 2);
        bPane.add(bEndGame, (Integer) 2);
        hideActions();
        
        listActions();
    }

    public void printOutput(String output){
        tArea.append(output+"\n");
    }

    public void hideActions(){
        bPane.remove(mLabel);
        bPane.remove(bAct);
        bPane.remove(bRehearse);
        bPane.remove(bMove);
        bPane.remove(bTake);
        bPane.remove(bUpgrade);
        bPane.remove(bEndTurn);
        bPane.remove(bEndGame);

    }

    public void listActions(){
        bPane.add(mLabel,(Integer) 2);
        bPane.add(bAct, (Integer) 2);
        bPane.add(bRehearse, (Integer) 2);
        bPane.add(bMove, (Integer) 2);
        bPane.add(bTake, (Integer) 2);
        bPane.add(bUpgrade, (Integer) 2);
        bPane.add(bEndTurn, (Integer) 2);
        bPane.add(bEndGame, (Integer) 2);
    }

    // create graphical card object, and add to scenes. identified by img tag
    public void buildCard(String img){
        JLabel cardlabel = new JLabel();
        ImageIcon cIcon =  new ImageIcon("./Images/"+img);
        cardlabel.setIcon(cIcon); 
        cards.put(img, cardlabel);
    }

    // put desired scene at location
    public void putCard(String name, int x, int y){
        JLabel label = cards.get(name);
        label.setBounds(x, y, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setOpaque(true);
        bPane.add(label, (Integer) 1);
    }

    // create cover card object, mapped to desired location by name
    public void addCoverCard(String name){
        JLabel cardlabel = new JLabel();
        ImageIcon cIcon =  new ImageIcon(COVER_IMG);
        cardlabel.setIcon(cIcon); 
        covers.put(name, cardlabel);
    }

    // put a cover card at desired location, naming convention is the location
    public void putCoverCard(String name, int x, int y){
        JLabel label = covers.get(name);
        label.setBounds(x, y, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setOpaque(true);
        bPane.add(label, (Integer) 2);
    }

    // update player info label with given stats
    public void updatePlayerInfo(int id, int money, int credits, int rehearse_tokens){
        infoLabel.setText("Current Player: "+id+"         Money: "+money+"          Credits:"+credits+"              Rehearse Tokens: "+rehearse_tokens+"               ");
    }

    // remove card of given name from pane
    public void removeCard(String name){
        JLabel label = cards.get(name);
        bPane.remove(label);
    }

    // remove cover of given name from pane (location name)
    public void removeCoverCard(String name){
        JLabel cover = covers.get(name);
        bPane.remove(cover);
    }

    // initialization of player icons
    public void buildPlayers(int num_players){
        for(int i = 0; i < num_players; i++){
            String img = "./Images/"+COLORS[i]+ "1.png";
            
            JLabel playerlabel = new JLabel();
            ImageIcon pIcon = new ImageIcon(img);
            playerlabel.setIcon(pIcon);
            playerlabel.setBounds(991 + (i * 10),248,46,46); // start players at trailers
            playerlabel.setVisible(true);
            bPane.add(playerlabel,(Integer) 3);
            players.put(i+1, playerlabel); // add player to list 

        }
    }

    public void updatePlayerLocation(int id, int x, int y){
        JLabel playerlabel = players.get(id);
        playerlabel.setBounds(x, y, playerlabel.getIcon().getIconWidth(), playerlabel.getIcon().getIconHeight());
    }
    
    // build a shot label, putting it at x and y coords. naming convention: {Scene}+{num} e.g. Saloon2
    public void buildShot(String name){
        JLabel sLabel = new JLabel();
        ImageIcon sIcon = new ImageIcon(SHOT_IMG);
        sLabel.setIcon(sIcon);
        shots.put(name, sLabel);
    }

    // put shot label on display at desired coords
    public void putShot(String name, int x, int y){
        JLabel label = shots.get(name);
        label.setBounds(x, y, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(true);
        bPane.add(label,(Integer) 3);
    }

    // make shot counter invisible
    public void removeShot(String name){
        JLabel label = shots.get(name);
        label.setVisible(false);
    }

    public String getPlayerCount(){
        String input = JOptionPane.showInputDialog(boardlabel, "How Many Players?");
        if(input == null){
            System.exit(-1);
        }
        return input;
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(boardlabel, message);
    }

    // update player die icon based on level
    public void updatePlayerLevel(int ID, int level){
        JLabel player = players.get(ID);
        String img = "./Images/"+COLORS[ID-1]+""+ level+".png";
        ImageIcon icon =  new ImageIcon(img);
        player.setIcon(icon);
    }


    /* Accessors */
    public JButton getbAct(){
        return bAct;
    }

    public JButton getbRehearse() {
        return bRehearse;
    }

    public JButton getbMove() {
        return bMove;
    }

    public JButton getbTake() {
        return bTake;
    }

    public JButton getbEndTurn() {
        return bEndTurn;
    }

    public JButton getbEndGame() {
        return bEndGame;
    }

    public JButton getbUpgrade() {
        return bUpgrade;
    }
}
