public class Upgrade extends GameObject{
    private int level;
    private int ammount;
    private String currency;
    
    public Upgrade(int level, int ammount, String currency, int x, int y, int width, int height){
        super(x, y, width, height);
        this.level = level;
        this.ammount = ammount;
        this.currency = currency;
    }
    
    /* Accessors */
    public int getLevel(){
        return this.level;
    }

    public int getAmmount(){
        return this.ammount;
    }

    public String getCurrency(){
        return this.currency;
    }
}
