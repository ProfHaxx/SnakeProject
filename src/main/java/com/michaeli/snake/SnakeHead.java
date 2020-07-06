package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;

public class SnakeHead {
    int x, y;
    SnakeComponent next;

    public SnakeHead() {
        this.next = new SnakeTail();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDead() {
        return next.isDead(x, y);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.x*App.COMPONENT_SIZE, this.y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
        next.paint(g);
    }

    public void move(int dx, int dy) {
        next.move(x,y);
        this.x += dx;
        this.y += dy;
    }

    public void grow() {
        SnakeBody body = new SnakeBody();
        body.setNext(next);
        next = body;
    }

    public int size() {
        return next.size() + 1;
    }

    @Override
    public String toString() {
        return "SnakeHead{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size() +
                '}';
    }
}
