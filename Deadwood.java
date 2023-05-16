public class Deadwood{
    public static void main(String[] args) {
        if(args.length == 1){
            int num_players = Integer.parseInt(args[0]);
            GameManager GM = new GameManager();
            GM. playGame(num_players);
        } else {
            System.out.println("Usage: Deadwood [num players]");
        }
        
    }
}