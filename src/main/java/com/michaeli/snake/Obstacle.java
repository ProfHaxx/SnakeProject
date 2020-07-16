package com.michaeli.snake;

import com.michaeli.snake.util.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {

    BufferedImage[] wall = new BufferedImage[] {
            Utility.getImage("wall/wall_3.png"),
            Utility.getImage("wall/wall_1.png"),
            Utility.getImage("wall/wall_4.png")
    };

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
        for(int i = 0; i < (App.WIDTH+App.HEIGHT)/25; i ++) {
            obstacles.add(generate(App.WIDTH / App.COMPONENT_SIZE, App.HEIGHT / App.COMPONENT_SIZE));
        }
    }

    public static Obstacle generate (int width, int height) {
        Random random = new Random();
        int x = random_between(3, width-3);
        int y = random_between(3, height-3);
        return new Obstacle(0, x, y);
    }

    public static int random_between (int untereGrenze, int obereGrenze) {
        int differenz = obereGrenze - untereGrenze;
        return (int) (Math.random() * differenz) + untereGrenze;
    }

    public void paint(Graphics2D g) {
        if(Snake.skin_id > 0) {
            g.drawImage(wall[Snake.skin_id-1], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        }
    }

}
