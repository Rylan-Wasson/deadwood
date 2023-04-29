public class Scene {

    private String name;
    private int shot_count;
    private int budget;

    public Scene(String name, int shot_count, int budget){
        this.name = name;
        this.shot_count = shot_count;
        this.budget = budget;
    }

    public String getName(){
        return this.name;
    }

    public int getShotCount(){
        return this.shot_count;
    }

    public int getBudget(){
        return this.budget;
    }

    public void setShotCount(int newCount){
        this.shot_count = newCount;
    }
}
