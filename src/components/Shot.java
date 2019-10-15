/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import javax.swing.JComponent;

/**
 *
 * @author Luat Dinh Bui
 */
public class Shot extends JComponent implements Serializable {

    //Determines the orientation of the "Shot" relative to parent
    private int direction;

    //Possible options to 'direction'
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int EAST = 3;
    public static final int WEST = 4;

    private int power;
    
    private int speed = 1;

    public Shot(JComponent parent) {
        
        this.direction = Shot.NORTH;
        this.setSize((int)(parent.getWidth() * 0.25), (int)(parent.getHeight() * 0.25));
        setInitialPosition(parent);
        
    }

    public Shot(JComponent parent, int direction) {
        
        this.direction = direction;
        this.power = 0;
        this.setSize((int)(parent.getWidth() * 0.25), (int)(parent.getHeight() * 0.25));
        setInitialPosition(parent);
        
    }

    public Shot(JComponent parent,int direction, int power ) {
        this.direction = direction;
        this.power = power;
        this.setSize((int)(parent.getWidth() * 0.25), (int)(parent.getHeight() * 0.25));
        setInitialPosition(parent);
    }
    
    private void setInitialPosition(JComponent parent) {
        int x = 0;
        int y = 0;
        
        switch (this.direction) {
            case Shot.NORTH:
                y = (int)(parent.getLocation().getY() - 2 - this.getSize().getHeight());
                x = (int)(parent.getLocation().getX() + (parent.getSize().getWidth()/2) 
                        - (this.getSize().getHeight()/2));
                break;
            case Shot.SOUTH:
                y = (int)(parent.getLocation().getY() + parent.getSize().getHeight() + 2);
                x = (int)(parent.getLocation().getX() + (parent.getSize().getWidth()/2)
                        - (this.getSize().getHeight()/2));
                break;
            case Shot.EAST:
                y = (int)(parent.getLocation().getY() + (parent.getSize().getHeight()/2) - 
                        (this.getSize().getHeight()/2));
                x = (int)(parent.getLocation().getX() - 2 - this.getSize().getWidth());
                break;
            case Shot.WEST:
                y = (int)(parent.getLocation().getY() + (parent.getSize().getHeight()/2) - 
                        (this.getSize().getHeight()/2));
                x = (int)(parent.getLocation().getX() + parent.getSize().getWidth() + 2);
                break;
        }
        
        this.setLocation(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.drawOval(0, 0, 5, 5);
    }
    
    
    
    public void moveShot() {
        int x = (int)this.getLocation().getX();
        int y = (int)this.getLocation().getY();
        switch (this.direction) {
            case Shot.NORTH:
                y -= speed;
                break;
            case Shot.SOUTH:
                y += speed;
                break;
            case Shot.EAST:
                x -= speed;
                break;
            case Shot.WEST:
                x += speed;
                break;
        }
        this.setLocation(x, y);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    
    

    
}