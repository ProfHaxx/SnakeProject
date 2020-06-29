package com.michaeli.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Snake extends JPanel {

    public SnakeHead head;
    public int orientation = 0; //Unit Circle: 90 deg. * orientation is snake's angle.
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

    public void setOrientation(int orientation) {
        if(Math.abs(this.orientation - orientation) != 2) {
            this.orientation = orientation;
        }
    }

    //Wrap-Around Movement?
    public void move() {
        //Condition if out of map
        if(orientation == 1) {
            head.move(0, -1);
        } else if(orientation == 2) {
            head.move(-1, 0);
        } else if(orientation == 3) {
            head.move(0, 1);
        } else {
            head.move(1, 0);
        }
    }

    public void grow() {
        head.grow();
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("[DEBUG] Panel Repaint");
        Graphics2D g2d = (Graphics2D) g;
        //Clear Image
        g2d.clearRect(0, 0, App.WIDTH, App.HEIGHT);
        //Draw Background
        //Draw Grid
        if(App.GRID) {
            grid(g2d);
        }
        //Draw Items
        //Draw Snake
        head.paint(g2d);
    }

    public void grid(Graphics2D g) {
        g.setColor(Color.BLACK);
        for(int i = 0; i*App.COMPONENT_SIZE <= WIDTH; i++) {
            for(int j = 0; j*App.COMPONENT_SIZE <= HEIGHT; j++) {
                g.drawLine(i*App.COMPONENT_SIZE, 0, i*App.COMPONENT_SIZE, HEIGHT);
                g.drawLine(0, j*App.COMPONENT_SIZE, WIDTH, j*App.COMPONENT_SIZE);
            }
        }
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head.toString() +
                ", orientation=" + orientation +
                ", dead=" + dead +
                '}';
    }
}
