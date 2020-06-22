package com.michaeli.snake;

import java.awt.Graphics2D;

public abstract class SnakeComponent {
    int x, y;
    SnakeComponent next;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SnakeComponent getNext() {
        return next;
    }

    public void setNext(SnakeComponent next) {
        this.next = next;
    }

    public void move(int x, int y) {
        next.move(this.x, this.y);
        this.x = x;
        this.y = y;
    }

    
    public abstract int size();
    public abstract void paint(Graphics2D g);
}
