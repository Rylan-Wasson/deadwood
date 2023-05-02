public class MainRole extends Role{
    private Scene scene;

    public MainRole(String name, String description, int rank, Scene scene){
        super(name, description, rank);
        this.scene = scene;
    }

    public Scene getScene(){
        return this.scene;
    }
}