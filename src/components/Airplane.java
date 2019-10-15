package components;

import javax.swing.JPanel;

public class Airplane extends ImageComponent {

    private int speed;
    
    private CollisionListener collisionListener;
    
    private short life;
    
    private final int DEFAULT_SIZE = 10;

    public Airplane() {
        this.imgSource = "/resources/airplane.png";
        this.readImage();
        this.speed = 1;
        this.life = 3;
        this.setSize(10);
        this.setLocation(0, 0);
    }

    //Para colocar el avion en la parte inferior central se necesitara el panel padre
    public Airplane(JPanel parent) {
        this.imgSource = "/resources/airplane.png";
        this.readImage();
        this.speed = 1;
        this.life = 3;
        this.setSize(5);
        this.setLocation((parent.getWidth() - this.getSize().width) / 2, parent.getHeight() - this.getSize().height);
    }
     
    
    public Airplane(JPanel parent, int speed) {
        this.imgSource = "/resources/airplane.png";
        this.readImage();
        this.speed = speed;
        this.life = 3;
        this.setSize(5);
        this.setLocation((parent.getWidth() - this.getSize().width) / 2, parent.getHeight() - this.getSize().height);
    }
    
    public Airplane(JPanel parent, int speed, short life) {
        this.imgSource = "/resources/airplane.png";
        this.readImage();
        this.speed = speed;
        this.life = life;
        this.setSize(5);
        this.setLocation((parent.getWidth() - this.getSize().width) / 2, parent.getHeight() - this.getSize().height);
    }

    public void movePlane(int horizontal, int vertical) {
        this.setLocation((int) (this.getLocation().getX() + (horizontal * speed)), (int) (this.getLocation().getY() + (vertical * speed)));
    }
    
    public void setSize(int proporcion){
         this.setSize(DEFAULT_SIZE*proporcion,DEFAULT_SIZE*proporcion);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addCollisionListener(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }

    public CollisionListener getCollisionListener() {
        return collisionListener;
    }

    public short getLife() {
        return life;
    }

    public void setLife(short life) {
        this.life = life;
    }
    
    public void reduceLife(short damage) {
        this.life -= damage;
        System.out.println(this.life);
    }
    
    
}
