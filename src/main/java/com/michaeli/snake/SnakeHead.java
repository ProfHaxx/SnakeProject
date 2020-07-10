package com.michaeli.snake;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class SnakeHead {
    int orientation;
    int x, y;
    SnakeComponent next;

    BufferedImage head = Utility.getSnakeImage("snake/snake_green.png")[0];

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

    public void pushOrientation(int orientation) {
        next.pushOrientation(this.orientation);
        this.orientation = orientation;
    }

    public void paint(Graphics2D g) {
//        g.setColor(Color.GREEN);
//        g.fillRect(this.x*App.COMPONENT_SIZE, this.y*App.COMPONENT_SIZE, App.COMPONENT_SIZE, App.COMPONENT_SIZE);
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(head, angle), x*App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
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
