public class ExtraRole extends Role{
    private Set set;

    public ExtraRole(String name, String description, int rank){
        super(name, description, rank);
    }

    public Set getSet(){
        return this.set;
    }
}