package com.michaeli.snake.snake;

import com.michaeli.snake.GameField;
import com.michaeli.snake.Snake;

import java.awt.Graphics2D;

public abstract class SnakeComponent {
    int x=-2, y=-2;
    int orientation = 0;
    int[] curved = new int[]{0,0};
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

    public boolean isDead(int x, int y) {
        return next.isDead(x, y) || (this.x == x && this.y == y);
    }

    public void sendDeath() {
        GameField.skin_id = 0;
        next.sendDeath();
    }

    public void rotSkin(int origin, int direction) {
        curved = new int[]{origin, direction};
    }

    public abstract int size();
    public abstract void paint(Graphics2D g);
    public abstract void pushOrientation(int orientation);
}
