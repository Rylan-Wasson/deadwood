public class Player {
    private int player_id;
    private int rank;
    private int rehearse_chips;
    private int cash;
    private int credits;
    private Role player_role;
    private Set player_location;


    // ----- constructor -----

    public Player(int player_id, int rank, int rehearse_chips, int cash, int credits, Set set){

    }

    

    // ----- accessors -----

    public int getCash() {
        return cash;
    }

    public int getCredits() {
        return credits;
    }

    public Set getPlayer_location() {
        return player_location;
    }

    public Role getPlayer_role() {
        return player_role;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public int getRank() {
        return rank;
    }

    public int getRehearse_chips() {
        return rehearse_chips;
    }
    

    // ----- mutators -----
    
    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setPlayer_location(Set location) {
        this.player_location = location;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setRehearse_chips(int rehearse_chips){
        this.rehearse_chips = rehearse_chips;
    }

    public void setPlayer_role(Role role) {
        this.player_role = role;
    }
}
