public class Role {
    protected String description;
    protected String name;
    protected int rank;
    protected boolean is_main_role;

    public Role(String name, String description, int rank, boolean is_main_role){
        this.description = description;
        this.name = name;
        this.rank = rank;
        this.is_main_role = is_main_role;
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
}
