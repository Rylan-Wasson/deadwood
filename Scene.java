import java.util.ArrayList;

public class Scene{

    private String name;
    private String line;
    private int budget;
    private int scene_num;
    private ArrayList<Role> roles;
    private String img;

    public Scene(String name, String line, int budget, int scene_num, String img){
        this.name = name;
        this.line = line;
        this.budget = budget;
        this.scene_num = scene_num;
        this.img = img;
    }

    public String getName(){
        return this.name;
    }

    public String getLine(){
        return this.line;
    }

    public int getBudget(){
        return this.budget;
    }

    public String getImg(){
        return img;
    }

    public ArrayList<Role> getRoles(){
        return this.roles;
    }

    public int getSceneNum(){
        return this.scene_num;
    }
    public void setRoles(ArrayList<Role> roles){
        this.roles = roles;
    }

    
}
