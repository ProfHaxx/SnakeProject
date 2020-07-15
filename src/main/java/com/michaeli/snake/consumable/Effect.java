package com.michaeli.snake.consumable;

import com.michaeli.snake.App;
import com.michaeli.snake.Utility;

public abstract class Effect {
    int id; 
    int duration;
    
    public Effect(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    public void start(int duration) {
        this.duration = duration;
        new Thread(() -> {
            while(this.duration > 0) {
                Utility.sleep(App.SPEED);
                this.duration--;
                tick();
            }
        }, "Effect Worker").start();
    }

    public boolean active() {
        return duration > 0;
    }

    public abstract void tick();
}
