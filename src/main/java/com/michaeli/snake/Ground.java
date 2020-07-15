package com.michaeli.snake;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Ground {
    public static BufferedImage[] ground = new BufferedImage[] {
            Utility.getImage("background/background_forest.png"),
            Utility.getImage("background/background_grass.png"),
            Utility.getImage("background/background_beach.png")
    };

    public static BufferedImage[] ground_decorative = new BufferedImage[] {
            Utility.getImage("background/background_forest_special.png"),
            Utility.getImage("background/background_grass_special.png"),
            Utility.getImage("background/background_beach_special.png")
    };

    public static ArrayList<BufferedImage> tiles = new ArrayList<>();

    public static void generateGround() {
        Random random = new Random();
        for(int i = 0; i < App.WIDTH/App.COMPONENT_SIZE; i++) {
            for(int j = 0; j < App.HEIGHT/App.COMPONENT_SIZE; j++) {
                if(random.nextDouble() < 0.95) {
                    tiles.add(ground[Snake.skin_id-1]);
                } else {
                    tiles.add(ground_decorative[Snake.skin_id-1]);
                }
            }
        }
    }

    public static void paintGround(Graphics2D g) {
        for (int i = 0; i < App.WIDTH / App.COMPONENT_SIZE; i++) {
            for (int j = 0; j < App.HEIGHT / App.COMPONENT_SIZE; j++) {
                g.drawImage(tiles.get(i*App.HEIGHT/App.COMPONENT_SIZE+j), i*App.COMPONENT_SIZE, j*App.COMPONENT_SIZE, null);
            }
        }
    }
}
