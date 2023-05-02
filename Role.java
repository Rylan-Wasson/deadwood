public class Role {
    protected String description;
    protected String name;
    protected int rank;

    public Role(String name, String description, int rank){
        this.description = description;
        this.name = name;
        this.rank = rank;
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
