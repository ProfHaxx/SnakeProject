package com.michaeli.snake;

import java.awt.*;

public class Consumable {
    int id, x, y;
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

    //Painting with if around id
    public void paint(Graphics2D g) {
        if(id == 0) {           //Apple
            g.setColor(Color.RED);
        } else if(id == 1) {    //Ghost Item
            g.setColor(Color.GRAY);
        }
        g.fillRect(x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
    }
}
