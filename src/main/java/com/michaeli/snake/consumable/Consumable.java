package com.michaeli.snake.consumable;

import com.michaeli.snake.App;
import com.michaeli.snake.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Consumable {
    int id, x, y;

    BufferedImage apple = Utility.getImage("food/food_apple.png");
    BufferedImage ghost = Utility.getImage("food/food_ghost.png");
    BufferedImage shadow = Utility.getImage("food/shadow_orb.png");
    BufferedImage speed_boost = Utility.getImage("food/speed_boost.png");
    BufferedImage speed_decrease = Utility.getImage("food/speed_decrease.png");

    public Consumable(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    //Painting with if around id
    public void paint(Graphics2D g) {
        if(id == 0) {
            g.drawImage(apple, x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else if(id == 1) {
            g.drawImage(ghost, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else if(id == 2) {
            g.drawImage(shadow, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else if(id == 3) {
            g.drawImage(speed_boost, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else if(id == 4) {
            g.drawImage(speed_decrease, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        }
    }
}
