package com.michaeli.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import static com.michaeli.snake.Obstacle.obstacles;

public class Snake extends JPanel {

    //Diverse Effect Counters.
    public int[] effect_counter = new int[1];

    //Effect Table
    //0: Ghost

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
                checkDead();
            }
        }, "Game Tick Worker");
        snakeWorker.start();
    }

    public void setOrientation(int orientation) {
        if(Math.abs(this.orientation - orientation) != 2) {
            this.orientation = orientation;
        }
    }

    //Wrap-Around Movement?
    public void move() {
        //Condition if out of map mit warp around
        System.out.println("[DEBUG] (" + head.getX() + "|" + head.getY() + ")");
        if (head.getX() < 0) {
            head.setX(App.WIDTH/App.COMPONENT_SIZE);
        }
        if (head.getX() > App.WIDTH/App.COMPONENT_SIZE) {
            head.x = 0;
        }
        if (head.getY() < 0) {
            head.setY(App.HEIGHT/App.COMPONENT_SIZE);
        }
        if (head.getY() > App.HEIGHT/App.COMPONENT_SIZE) {
            head.y = 0;
        }

        //Direction Switch
        if(orientation == 1) {
            head.move(0, -1);
        } else if(orientation == 2) {
            head.move(-1, 0);
        } else if(orientation == 3) {
            head.move(0, 1);
        } else {
            head.move(1, 0);
        }

        int foodIndex = -1;
        //List to avoid ConcurrentModificationException
        ArrayList<Consumable> temporaryList = new ArrayList<>(ConsumableFactory.consumables);
        for(Consumable consumable:temporaryList) {
            if(head.getX() == consumable.getX() && head.getY() == consumable.getY()) {
                foodIndex = ConsumableFactory.consumables.indexOf(consumable);
            }
        }
        if(foodIndex != -1) {
            Consumable food = ConsumableFactory.consumables.get(foodIndex);
            if(food.getId() == 0) {
                grow();
            } else if(food.getId() == 1) {
                startEffect(0, 10);
            }
            ConsumableFactory.consumables.remove(foodIndex);
        }

        //condition if Snake hits Obstacle
        //AayList<Obstacle> temporaryList1 =new ArrayList<>(obstacles);
        for(Obstacle obstacle:obstacles)
            if(head.getX() == obstacle.getX() && head.getY() == obstacle.getY()) {
            die();
            }
        }

    public void die() {
        dead = true;
        System.out.println("GAME OVER"); //evtl neues Fenster aufpoppen lassen?
        //Score?
    }

    public void checkDead() {
        dead = head.isDead() && effect_counter[0] == 0;
    }

    public void grow() {
        head.grow();
    }

    public void startEffect(int effectId, int duration) {
        new Thread(() -> {
            effect_counter[effectId] = duration;
            while(effect_counter[effectId] > 0) {
                Utility.sleep(1000);
                effect_counter[effectId]--;
            }
        }, "Effect Worker").start();
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
        ConsumableFactory.consumables.forEach(e -> e.paint(g2d));
        //Draw Obstacles
        obstacles.forEach(e-> e.paint(g2d));
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
