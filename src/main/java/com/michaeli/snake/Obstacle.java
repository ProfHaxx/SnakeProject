package com.michaeli.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
    int id, x, y;
    public Obstacle (int id, int x, int y) {
        this.id = id; //id lasse ich mal drin, für den Fall, dass wir verschiedene Hindernisse implementieren wollen
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    public static void spawnObstacles() {
        for(int i = 0; i < 20; i ++) {
            obstacles.add(generate(App.WIDTH / App.COMPONENT_SIZE, App.HEIGHT / App.COMPONENT_SIZE));
        }
    }

    public static Obstacle generate (int width, int height) {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        return new Obstacle(0, x, y);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.x*App.COMPONENT_SIZE, this.y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
    }

}
