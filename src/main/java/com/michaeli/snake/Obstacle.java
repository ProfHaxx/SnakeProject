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
            Utility.getImage("wall/wall_4.png"),
            Utility.getImage("wall/wall_2.png")
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
        obstacles.clear();
        for(int i = 0; i < (App.WIDTH+App.HEIGHT)/10; i ++) {
            obstacles.add(generate(1,App.WIDTH / App.COMPONENT_SIZE, App.HEIGHT / App.COMPONENT_SIZE));
        }
    }

    public static Obstacle generate (int mapNumber,int width, int height) {
        Random random = new Random();
        double rand = random.nextDouble();
        int x = random_between(3, width-4);
        int y = random_between(3, height-4);
        if (mapNumber == 1) {
            if (rand <= 0.4) {
                return new Obstacle(1, x, y );
            } else if (rand > 0.4 && rand <= 0.5) {
                return new Obstacle(0, 2, y);
            } else if (rand > 0.5 && rand <= 0.6) {
                return new Obstacle(0, width-4, y);
            } else if (rand > 0.6 && rand <= 0.8) {
                return new Obstacle(0, x, 2);
            } else if (rand > 0.8) {
                return new Obstacle(0, x, height-4);
            }
        } else if (mapNumber == 2) {
            if (rand <= 0.2) {
                return new Obstacle(0, 5, y);
            } else if (rand > 0.2 && rand <= 0.4) {
                return new Obstacle(0, 10, y);
            } else if (rand > 0.4 && rand <= 0.6) {
                return new Obstacle(0, 16, y);
            } else if (rand > 0.6 && rand <= 0.8) {
                return new Obstacle(0, 22, y);
            } else if (rand > 0.8) {
                return new Obstacle(0, 27, y);
            }
        }  else if (mapNumber == 3) {
            if (rand <= 0.33) {
                return new Obstacle(0, x, 5);
            } else if (rand > 0.33 && rand <= 0.66) {
                return new Obstacle(0, x, 10);
            } else if (rand > 0.66) {
                return new Obstacle(0, x, 15);
            }
        } else if (mapNumber == 4) {
            return new Obstacle(1, x, y );
        }

        return new Obstacle (-1,-1,-1);
    }

    public static int random_between (int untereGrenze, int obereGrenze) {
        int differenz = obereGrenze - untereGrenze;
        return (int) (Math.random() * differenz) + untereGrenze;
    }

    public void paint(Graphics2D g) {
        if(GameField.skin_id > 0) {
            g.drawImage(wall[GameField.skin_id-1], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        }
    }

}
