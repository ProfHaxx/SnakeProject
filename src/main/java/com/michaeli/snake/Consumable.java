package com.michaeli.snake;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Consumable {
    int id, x, y;

    BufferedImage apple = Utility.getImage("food/food_apple.png");
    BufferedImage ghost = Utility.getImage("food/food_ghost.png");

    public Consumable(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        System.out.println("[DEBUG] Successfully created Food Object");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Painting with if around id
    public void paint(Graphics2D g) {
        if(id == 0) {           //Apple
            g.drawImage(apple, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else if(id == 1) {    //Ghost Item
            g.drawImage(ghost, x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        }
    }
}
