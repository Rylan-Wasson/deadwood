public class Deadwood{
    public static void main(String[] args) {
        //TODO: player count error handling
        int num_players = Integer.parseInt(args[0]);
        GameManager GM = new GameManager();
        GM.setupGame(num_players);
    }
}