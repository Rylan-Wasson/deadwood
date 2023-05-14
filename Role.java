public class Role {
    protected String description;
    protected String name;
    protected int rank;
    protected boolean is_main_role;
    protected boolean taken;

    public Role(String name, String description, int rank, boolean is_main_role){
        this.description = description;
        this.name = name;
        this.rank = rank;
        this.is_main_role = is_main_role;
        this.taken = false;
    }

    public String getDescription(){
        return this.description;
    }

    public String getName(){
        return this.name;
    }

    public int getRank(){
        return this.rank;
    }
    
    public boolean getIsMainRole(){
        return this.is_main_role;
    }

    public boolean isTaken(){
        return taken;
    }

    public void setTaken(boolean status){
        taken = status;
    }

    
}
