import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private int num_days;
    private int num_players;
    private ArrayList<Player> players = new ArrayList<Player>();
    private final int dice_sides = 6;
    private XmlParser xmlParser;
    private GameBoard gameBoard;
    private GuiController guiController;
    private LocationManager locationManager;
    private TurnManager turnManager;
    private boolean end_game;
    private ArrayList<Scene> scenes;
    private int current_player_index;
    private Turn turn;

    public GameManager(){
        this.xmlParser = new XmlParser();
        this.gameBoard = new GameBoard(xmlParser.parseBoardXML());
        this.scenes = xmlParser.parseCardsXML();
        this.locationManager = new LocationManager(gameBoard, this);
        this.guiController = new GuiController(scenes, gameBoard.getLocations(), this);
    }

    public int getNumDays(){
        return this.num_days;
    }

    public int getNumPlayers(){
        return this.num_players;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    //Setters
    public void setNumDays(int num_days){
        this.num_days = num_days;
    }

    public void setNumPlayers(int num_players){
        this.num_players = num_players;
    }

    /*
     * UTIL FUNCTIONS
     */

    /* ENTRY POINT TO DEADWOOD
     * playGame()
     */
     public void playGame(){
        boolean validPlayerCount = false;

        //getting player count
        while(!validPlayerCount){
            num_players = 0;
            try {
                num_players = Integer.parseInt(guiController.getPlayerCount());
                if(num_players >= 2 && num_players <= 8){
                    validPlayerCount = true;
                } else {
                    guiController.displayMessage("Invalid Player Count!", "Usage: 2 - 8 players");
                }
            } catch (Exception e) {
                guiController.displayMessage("Invalid Input", "Usage: 2 - 8 players");
            }
        }

        setupGame();

        //update the player info label
        guiController.updatePlayerInfo(turn.getActivePlayer());
        end_game = false;
     }



    public void checkGameStatus(){
        if(num_days == 0 || turn.gameOver() == true){ // game over
            scorePlayers();
            System.exit(0);
            
        } else if(gameBoard.getNumActiveScenes() <= 1){ // day over
            num_days--;
            setupDay();
            guiController.displayMessage("Days Remaining", Integer.toString(num_days)); //Days remaining popup

        } else if(turn.turnActive() == false){ // turn over
            if(current_player_index == (players.size() - 1)){
                current_player_index = 0;
            } else {
                current_player_index++;
            }
            turn = new Turn(players.get(current_player_index));
            //TODO update info
            //turnManager.setTurn(turn);
            guiController.updatePlayerInfo(turn.getActivePlayer());
        }
    }

    /*
     * setupGame()
     * Sets up the game, utilizes the GuiController to recieve input
     */
    public void setupGame(){

        guiController.initPlayers(num_players);
        guiController.buildShots(gameBoard.getLocations());
        // Creates all the players based on player count, adds them to the players LinkedList
        for(int i = 1; i <= num_players; i++){
            Player new_player = null;
            switch(num_players){
                case 5:
                    new_player = new Player(i, 1, 0, 0, 2);
                    break;
                case 6:
                    new_player = new Player(i, 1, 0, 0, 4);
                    break;
                case 7:
                    new_player = new Player(i, 2, 0, 0, 0);
                    break;
                case 8:
                    new_player = new Player(i, 2, 0, 0, 0);
                    break;
                default:
                    new_player = new Player(i, 1, 0, 0, 0);
                    break;
            }
            this.players.add(new_player);
        }

        //Sets the day cap based on player count
        switch(num_players){
            case 2:
                setNumDays(3);
                break;
            case 3:
                setNumDays(3);
                break;
            default:
                setNumDays(4);
                break;
        }

        //sets up the day
        setupDay();

        //Starting player
        current_player_index = 0;

        this.turn = new Turn(players.get(current_player_index));
        this.turnManager = new TurnManager(guiController, locationManager, turn, this);
    }

    //Sets up a new day
    private void setupDay(){

        //update locationManager with new player locations
        locationManager.moveAllPlayers(players, gameBoard.getLocationByName("Trailer"));
        //update the gui for new player locations
        for(Player player : players){
            guiController.updatePlayerLocation(player.getPlayerID(), locationManager.getLocationByName("Trailer"));
        }
        
        gameBoard.distributeScenes(scenes);
        guiController.distributeScenes(gameBoard.getLocations());
        guiController.distributeShotCounters(gameBoard.getLocations());
        
    }

    /* Calculate winner(s) of game, and announce to players */
    public void scorePlayers(){
        ArrayList<Player> winners = new ArrayList<Player>();
        winners.add(players.get(0)); // initialize winner
        for(int i = 1; i < players.size(); i++){ // compare scores, put winning player(s) in winners list
            Player cur = players.get(i);
            if(cur.getScore() > winners.get(0).getScore()){ // higher score than anyone in list, reset and add them
                winners.clear();
                winners.add(cur);
            } else if(cur.getScore() == winners.get(0).getScore()) { // tied for high score, add to winners
                winners.add(cur);
            }
        }
       guiController.listWinners(winners);
    }

    /*
     * Rolls X dice and returns an int[] of the generate rolls 
     */
    public int[] rollXDice(int num_dice){
        Random randomInt = new Random();
        int[] dice_outcomes = new int[num_dice];

        for(int i = 0; i < num_dice; i++){
            dice_outcomes[i] = (randomInt.nextInt(dice_sides) + 1); //exclusive upper bound
        }
        return dice_outcomes;
    }
}
