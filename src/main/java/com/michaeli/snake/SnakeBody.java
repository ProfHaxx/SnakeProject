package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;

public class SnakeBody extends SnakeComponent {
    @Override
    public void paint(Graphics2D g) {
        //For now a simple colored Rectangle will do.
        g.setColor(Color.RED);
        g.fillRect(App.COMPONENT_SIZE*this.x, App.COMPONENT_SIZE*this.y, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
        next.paint(g);
    }

    @Override
    public int size() {
        return next.size() + 1;
    }
}
