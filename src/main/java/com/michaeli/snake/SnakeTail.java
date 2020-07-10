package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class SnakeTail extends SnakeComponent {

    BufferedImage tail = Utility.getSnakeImage("snake/snake_green.png")[2];

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics2D g) {
        //For this, I'll just use a different color
//        g.setColor(Color.BLUE);
//        g.fillRect(this.x*App.COMPONENT_SIZE, this.y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(tail, angle), x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
    }

    @Override
    public boolean isDead(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public void pushOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public int size() {
        return 1;
    }
}
