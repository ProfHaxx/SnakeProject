package com.michaeli.snake.snake;

import com.michaeli.snake.App;
import com.michaeli.snake.GameField;
import com.michaeli.snake.Snake;
import com.michaeli.snake.util.Utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SnakeHead {
    int orientation;
    int x, y;
    SnakeComponent next;

    BufferedImage[] skin = new BufferedImage[]{
        Utility.getSnakeImage("snake/snake_dead.png")[0],
            Utility.getSnakeImage("snake/snake_green.png")[0],
            Utility.getSnakeImage("snake/snake_red.png")[0],
            Utility.getSnakeImage("snake/snake_blue.png")[0]
    };

    public SnakeHead() {
        this.next = new SnakeTail();
    }

    public SnakeHead(int x, int y, int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
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
        int currentOrientation = this.orientation;
        next.pushOrientation(currentOrientation);
        if(!(this.orientation == orientation)) next.rotSkin(this.orientation, orientation);
        this.orientation = orientation;
    }

    public void paint(Graphics2D g) {
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(skin[GameField.skin_id], angle), x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
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

    public void sendDeath() {
        GameField.skin_id = 0;
        next.sendDeath();
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
