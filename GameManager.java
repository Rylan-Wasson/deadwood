import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private int num_days;
    private int num_players;
    private ArrayList<Player> players = new ArrayList<Player>();
    private final int dice_sides = 6;
    private XmlParser xmlParser;
    private GameBoard gameBoard;
    private TextController textController;
    private LocationManager locationManager;
    private TurnManager turnManager;
    private boolean end_game;
    private ArrayList<Scene> scenes;

    public GameManager(){
        this.xmlParser = new XmlParser();
        this.gameBoard = new GameBoard(xmlParser.parseBoardXML());
        this.scenes = xmlParser.parseCardsXML();
        this.textController = new TextController();
        this.locationManager = new LocationManager(gameBoard);
        this.turnManager = new TurnManager(textController, locationManager);
    }

    //GetterslocationManager.moveAllPlayers(players, gameBoard.getLocationByName("Trailer"));
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

     public void playGame(int num_players){
        int starting_player_id = setupGame(num_players);
        end_game = false;
        
        while(!end_game){
            end_game = playDay(starting_player_id);
            if(gameBoard.getNumActiveScenes() == 1){
                setupDay();
            }
        }
     }

    /*
     * playDay()
     */
    private boolean playDay(int starting_player_id){
        end_game = turnManager.conductTurn(players.get(starting_player_id - 1));
        return end_game;
    }

    /*
     * setupGame()
     * Sets up the game, utilizes the TextController to recieve input
     * returns the id of the starting player
     */
    public int setupGame(int num_players){
        int starting_player_id = 1;

        setNumPlayers(num_players);

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

        //returns the starting player id
        return starting_player_id;
    }

    //Sets up a new day
    private void setupDay(){
        locationManager.moveAllPlayers(players, gameBoard.getLocationByName("Trailer"));
        gameBoard.distributeScenes(scenes);
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
