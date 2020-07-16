package com.michaeli.snake.consumable;

import com.michaeli.snake.App;
import com.michaeli.snake.util.Utility;

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
                while(food > 0 && refresh_counter < 10) {
                    refresh_counter++;
                    Utility.sleep(1000);
                }
                refresh_counter = 0;
                spawn();
            }
        });
        foodWorker.start();
    }

    public static void eat(int index) {
        food--;
        consumables.remove(index);
    }

    public static void spawn() {
        System.out.println("[Debug/Food] Food Spawned!");
        consumables.add(generate(App.WIDTH/App.COMPONENT_SIZE, App.HEIGHT/App.COMPONENT_SIZE));
        food++;
    }
}
