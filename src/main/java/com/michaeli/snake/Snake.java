package com.michaeli.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Snake extends JPanel {

    public SnakeHead head;
    public int orientation = 1; //Einheitskreis: 90*orientation ist der Winkel, in welchen Snake gerade sieht.
    public boolean dead = false;

    Thread snakeWorker;

    public Snake() {
        head = new SnakeHead();

    }

    public void spawnSnake() {
        snakeWorker = new Thread(() -> {
            while(!dead) {
                Utility.sleep(App.SPEED);
                move();
                repaint();
            }
        });
        snakeWorker.start();
    }

    //Wrap-Around Movement?
    public void move() {
        //Condition if out of map
        if(orientation == 1) {
            head.move(head.getX(), head.getY() - 1);
        } else if(orientation == 2) {
            head.move(head.getX() - 1, head.getY());
        } else if(orientation == 3) {
            head.move(head.getX(), head.getY() + 1);
        } else {
            head.move(head.getX() + 1, head.getY());
        }
    }

    public void paint(Graphics g) {
        System.out.println("[DEBUG] Panel Repaint");
        head.paint((Graphics2D) g);
    }
}
