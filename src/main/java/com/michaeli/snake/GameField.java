package com.michaeli.snake;

import com.michaeli.snake.consumable.ConsumableFactory;
import com.michaeli.snake.util.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.michaeli.snake.Obstacle.obstacles;

public class GameField extends JPanel {
    ArrayList<Snake> snakes = new ArrayList<>();
    ArrayList<Thread> snakeWorkers = new ArrayList<>();

    public GameField(int players, boolean local) {
        if(players == 1) { // Local Single Player
            snakes.add(new Snake());
        } else if (players == 2 && local) { // Local HotSeat
            snakes.add(new Snake(1));
            snakes.add(new Snake(2));
        } else { // Remote Multiplayer
            //Very long TODO
            System.out.println("This function is not supported yet!");
        }
    }

    public void launch() {
        snakes.forEach(snake -> snakeWorkers.add(new Thread(() -> {
            while(!snake.dead) {
                Utility.sleep(App.SPEED);
                snake.move();
                snake.wrapAround();
                snake.checkFood();
                snake.checkDead();
                this.repaint();
            }
        }, "Game Tick Worker")));

        snakeWorkers.forEach(Thread::start);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("[DEBUG] Panel Repaint");
        Graphics2D g2d = (Graphics2D) g;

        //Clear Image
        g2d.clearRect(0, 0, App.WIDTH, App.HEIGHT);

        //Draw Background
        Ground.paintGround(g2d);

        //Draw Items
        ConsumableFactory.consumables.forEach(e -> e.paint(g2d));

        //Draw Obstacles
        obstacles.forEach(e-> e.paint(g2d));

        //Draw Snake
        snakes.forEach(snake -> snake.head.paint(g2d));

        //Singleplayer-Only
        if(snakes.size() == 1) {
            //Shadow Orb
            if(snakes.get(0).effects[1].active() && !snakes.get(0).copying) {
                snakes.get(0).nearSight(g2d);
            }
        }
    }
}
