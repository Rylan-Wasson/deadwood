public class ShotCounter extends GameObject{
    protected boolean active;
    protected int num;
    public ShotCounter(int num, int x, int y, int width, int height){
        super(x, y , width, height);
        this.num = num;
        active = true;
    }

    public void setActive(Boolean condition){
        this.active = condition;
    }
}
