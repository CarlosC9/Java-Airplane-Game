/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import components.Airplane;
import components.Asteroid;
import components.CollisionEvent;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JOptionPane;
import views.GameFrame;
import views.GamePanel;
import views.StartPanel;

public class MainController {

    private GamePanel panel;
    private GameFrame frame;
    private PlaneController planeController;
    private volatile ArrayList<Asteroid> asteroids;
    private Airplane plane;
    private Timer asteroidsTimer;
    private Timer gameTimer;

    public MainController() {
        this.frame = new GameFrame();
        this.frame.setLocationRelativeTo(null);
        initiateGame();
    }

    private void initiateGame() {

        this.panel = new GamePanel();
        this.frame.setContentPane(panel);
        this.frame.revalidate();
        this.frame.repaint();

        asteroids = new ArrayList<>();
        short vidas = 0;
        
        boolean valid = false;
        do {
        try {
            StartPanel startPanel = new StartPanel(this.frame, true);
            valid = true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Debes introducir un número"
                    + " entero", "ERROR", JOptionPane.ERROR_MESSAGE);
        }} while(!valid);

        
        this.plane = new Airplane(this.panel);
        this.plane.setLife(vidas);
        this.panel.setPlane(this.plane);
        this.panel.setLifePlane(this.plane.getLife());
        this.planeController = new PlaneController(this.panel, this.frame);

        gameTimer = new Timer();
        gameTimer.schedule(new RunGame(), 0, 7);
        
        
        asteroidsTimer = new Timer();

        asteroidsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Asteroid asteroid = new Asteroid();
                int randomX = (int) (Math.random() * (panel.getWidth() - asteroid.getWidth()));
                asteroid.setLocation(randomX, 20);
                panel.add(asteroid);
                panel.repaint();
                asteroids.add(asteroid);
            }
        }, 0, 1000);
    }

    private class RunGame extends TimerTask {

        @Override
        public void run() {

            int horizontal = 0;
            int vertical = 0;
            if (planeController.getNorth().get() && plane.getY()> 0) {
                horizontal--;
            }
            if (planeController.getSouth().get() && plane.getY() < (panel.getHeight() - plane.getHeight())) {
                horizontal++;
            }
            if (planeController.getEast().get() && plane.getX() < (panel.getWidth() - plane.getWidth())) {
                vertical++;
            }
            if (planeController.getWest().get() && plane.getX() > 0) {
                vertical--;
            }
            plane.movePlane(vertical, horizontal);
            
            ArrayList<Asteroid> asteroidsClone = (ArrayList<Asteroid>) asteroids.clone();
            for (Asteroid asteroid : asteroidsClone) {
                asteroid.move();
                if (asteroid.getBounds().intersects(plane.getBounds())) {
                    panel.remove(asteroid);
                    asteroids.remove(asteroid);
                    panel.repaint();
                    plane.getCollisionListener().onCollision(new CollisionEvent(plane, asteroid));
                    if (plane.getLife() <= 0) {
                        stopGame();
                    }
                } else if (asteroid.getY() > panel.getHeight() + 100) {
                    panel.remove(asteroid);
                    asteroids.remove(asteroid);
                    panel.repaint();
                }
            }
            
        }

        private void stopGame() {
            asteroidsTimer.cancel();
            asteroidsTimer.purge();
            gameTimer.cancel();
            gameTimer.purge();
            JOptionPane.showMessageDialog(frame, "Game Over", "YOU LOSE", JOptionPane.WARNING_MESSAGE);
            initiateGame();
        }

    }

}
