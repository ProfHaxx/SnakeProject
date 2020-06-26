package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;

public class SnakeTail extends SnakeComponent {
    
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics2D g) {
        //For this, I'll just use a different color
        g.setColor(Color.BLUE);
        g.fillRect(this.x*App.COMPONENT_SIZE, this.y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
    }

    @Override
    public int size() {
        return 1;
    }
    //hallo testY
}
