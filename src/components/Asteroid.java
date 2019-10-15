package components;


public class Asteroid extends ImageComponent{
    
    private short damage;
    private int speed; 
    
    public Asteroid() {
        this.speed=1;
        this.imgSource = "/resources/asteroid.png";
        this.readImage();
        this.damage = 1;
        this.setSize(50);
    }
    
    public Asteroid(short damage) {
        this.speed = 1;
        this.imgSource = "/resources/asteroid.png";
        this.readImage();
        this.damage = damage;
        this.setSize(50);
    }
    
    public void move() {
        this.setLocation((int )this.getLocation().getX(), (int) this.getLocation().getY() + this.speed);
    }
 
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public short getDamage() {
        return damage;
    }

    public void setDamage(short damage) {
        this.damage = damage;
    }
    
    public void setSize(int proporcion) {
        this.setSize(proporcion, proporcion);
    }
    
    
    
}
