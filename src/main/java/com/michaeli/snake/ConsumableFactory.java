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
        //Apple, Movement Speed (Buff/Debuff)
        if(rand <= 0.8) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            return new Consumable(0, x, y);
        } else if(rand > 0.8){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            return new Consumable(1, x, y);
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
