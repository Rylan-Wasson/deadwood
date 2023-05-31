public class Deadwood{
    public static void main(String[] args) {
        if(args.length == 1){
            int num_players = Integer.parseInt(args[0]);
            if(num_players > 8 || num_players < 2){
                System.err.println("Usage: Players = [2,8]");
            } else {
                GameManager GM = new GameManager();
                GM. playGame(num_players);
            }
        } else {
            System.err.println("Usage: Deadwood [num players]");
        }
    }
}