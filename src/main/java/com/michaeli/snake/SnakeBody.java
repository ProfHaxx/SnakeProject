package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class SnakeBody extends SnakeComponent {

    BufferedImage dead_body = Utility.getSnakeImage("snake/snake_dead.png")[1];
    BufferedImage body = Utility.getSnakeImage("snake/snake_green.png")[1];

    @Override
    public void paint(Graphics2D g) {
        //For now a simple colored Rectangle will do.
//        g.setColor(Color.RED);
//        g.fillRect(App.COMPONENT_SIZE*this.x, App.COMPONENT_SIZE*this.y, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(body, angle), x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        next.paint(g);
    }

    @Override
    public void pushOrientation(int orientation) {
        next.pushOrientation(this.orientation);
        this.orientation = orientation;
    }

    @Override
    public int size() {
        return next.size() + 1;
    }
}
