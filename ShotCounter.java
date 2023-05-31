public class ShotCounter extends GameObject{
    protected boolean active;

    public ShotCounter(int x, int y, int width, int height){
        super(x, y , width, height);
        active = true;
    }

    public void setActive(Boolean condition){
        this.active = condition;
    }
}
