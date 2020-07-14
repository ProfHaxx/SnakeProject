package com.michaeli.snake;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class ConsumableFactory {

    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static Thread foodWorker;
    public static int food;

    public static Consumable generate(int width, int height) {
        food++;
        Random random = new Random();
        double rand = random.nextDouble();
        
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        // Weighted Random Generator
        if(rand <= 0.6) {
            return new Consumable(0, x, y);
        } else if(rand > 0.6 && rand <= 0.7){
            return new Consumable(1, x, y);
        } else if(rand > 0.7 && rand <= 0.8) {
            return new Consumable(2, x, y);
        } else if(rand > 0.8 && rand <= 0.9) {
            return new Consumable(3, x, y);
        } else if(rand > 0.9) {
            return new Consumable(4, x, y);
        }

        return new Consumable(-1, -1, -1);
    }

    public static void start() {
        foodWorker = new Thread(() -> {
            int refresh_counter = 0;
            while(true) {
                if(food <= 3) {
                    for(int i = 0; i < 30; i++) {
                        Utility.sleep(1000);
                        if(food == 0) {
                            consumables.add(generate(App.WIDTH/App.COMPONENT_SIZE, App.HEIGHT/App.COMPONENT_SIZE));
                            refresh_counter = 0;
                            break;
                        }
                        refresh_counter++;
                    }
                    if(refresh_counter == 30) {
                        consumables.add(generate(App.WIDTH/App.COMPONENT_SIZE, App.HEIGHT/App.COMPONENT_SIZE));
                        refresh_counter = 0;
                    }
                }
            }
        });
        foodWorker.start();
    }
}
