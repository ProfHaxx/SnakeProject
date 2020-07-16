package com.michaeli.snake.consumable;

import com.michaeli.snake.App;
import com.michaeli.snake.GameField;
import com.michaeli.snake.Snake;
import com.michaeli.snake.util.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Consumable {
    int id, x, y;

    BufferedImage[] apple = new BufferedImage[]{
            Utility.getImage("food/food_mushroom.png"),
            Utility.getImage("food/food_apple.png"),
            Utility.getImage("food/food_fish.png")
    };

    BufferedImage[] ghost = new BufferedImage[]{
            Utility.getImage("food/food_ghost.png")
    };

    BufferedImage[] shadow = new BufferedImage[]{
            Utility.getImage("food/shadow_orb.png")
    };

    BufferedImage[] speed_boost = new BufferedImage[] {
            Utility.getImage("food/speed_boost.png")
    };

    BufferedImage[] speed_decrease = new BufferedImage[]{
            Utility.getImage("food/speed_decrease.png")
    };

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
        if(GameField.skin_id > 0) {
            if(id == 0) {
                g.drawImage(apple[GameField.skin_id-1], x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
            } else if(id == 1) {
                g.drawImage(ghost[0], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
            } else if(id == 2) {
                g.drawImage(shadow[0], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
            } else if(id == 3) {
                g.drawImage(speed_boost[0], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
            } else if(id == 4) {
                g.drawImage(speed_decrease[0], x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
            }
        }
    }
}
