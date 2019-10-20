package controller;

import components.Airplane;
import components.Asteroid;
import components.CollisionEvent;
import components.CollisionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import views.GameFrame;
import views.GamePanel;


public class PlaneController {

    private GamePanel panel;
    private Airplane plane;
    private GameFrame frame;
    private AtomicBoolean north;
    private AtomicBoolean south;
    private AtomicBoolean east;
    private AtomicBoolean west;

    public PlaneController(GamePanel panel, GameFrame frame) {
        this.frame = frame;
        this.panel = panel;
        
        this.plane = this.panel.getPlane();
        this.plane.addCollisionListener(new CollisionListener() {
            @Override
            public void onCollision(CollisionEvent evt) {
               Asteroid asteroid = (Asteroid) evt.getCollisionObject();
               Airplane plane = (Airplane) evt.getSource();
               plane.reduceLife(asteroid.getDamage());
               panel.changeLife(plane.getLife());
               System.out.println("The airplane collided!");
            }
        });
        
        
        this.north = new AtomicBoolean();
        this.south = new AtomicBoolean();
        this.east = new AtomicBoolean();
        this.west = new AtomicBoolean();
        

        InitiateKeyListeners();
    }

    private void InitiateKeyListeners() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ev) {
                
            }

            @Override
            public void keyPressed(KeyEvent ev) {
                if (ev.getKeyCode() == KeyEvent.VK_W) {
                    north.set(true);
                }
                if (ev.getKeyCode() == KeyEvent.VK_S) {
                    south.set(true);
                }
                if (ev.getKeyCode() == KeyEvent.VK_D) {
                    east.set(true);
                }
                if (ev.getKeyCode() == KeyEvent.VK_A) {
                    west.set(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent ev) {
                if (ev.getKeyCode() == KeyEvent.VK_W) {
                    north.set(false);
                }
                if (ev.getKeyCode() == KeyEvent.VK_S) {
                    south.set(false);
                }
                if (ev.getKeyCode() == KeyEvent.VK_D) {
                    east.set(false);
                }
                if (ev.getKeyCode() == KeyEvent.VK_A) {
                    west.set(false);
                }
            }
        });

    }

    public AtomicBoolean getSouth() {
        return south;
    }

    public AtomicBoolean getEast() {
        return east;
    }

    public AtomicBoolean getWest() {
        return west;
    }

    public AtomicBoolean getNorth() {
        return north;
    }

    public Airplane getPlane() {
        return plane;
    }

   
    
    
}
