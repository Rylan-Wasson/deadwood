public class Player {
    private int player_id;
    private int rank;
    private int rehearse_chips;
    private int cash;
    private int credits;
    private Role player_role;

    // ----- constructor -----

    public Player(int player_id, int rank, int rehearse_chips, int cash, int credits){
        this.player_id = player_id;
        this.rank = rank;
        this.rehearse_chips = rehearse_chips;
        this.cash = cash;
        this.credits = credits;
    }

    /* Resets all fields tied to the current role */
    public void resetRoleStatus(){
        this.player_role.setTaken(false);
        this.rehearse_chips = 0;
        this.player_role = null;
    }

    // ----- accessors -----

    public int getCash() {
        return cash;
    }

    public int getCredits() {
        return credits;
    }

    public Role getPlayerRole() {
        return player_role;
    }

    public int getPlayerID() {
        return player_id;
    }

    public int getRank() {
        return rank;
    }

    public int getRehearseChips() {
        return rehearse_chips;
    }
    

    // ----- mutators -----
    
    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }



    public void setPlayerID(int player_id) {
        this.player_id = player_id;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setRehearse_chips(int rehearse_chips){
        this.rehearse_chips = rehearse_chips;
    }

    public void setPlayerRole(Role role) {
        this.player_role = role;
    }
}
