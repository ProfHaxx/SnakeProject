package com.michaeli.snake.snake;

import com.michaeli.snake.App;
import com.michaeli.snake.Snake;
import com.michaeli.snake.util.Utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SnakeTail extends SnakeComponent {

    BufferedImage[] skin = new BufferedImage[]{
            Utility.getSnakeImage("snake/snake_dead.png")[2],
            Utility.getSnakeImage("snake/snake_green.png")[2],
            Utility.getSnakeImage("snake/snake_red.png")[2],
            Utility.getSnakeImage("snake/snake_blue.png")[2]
    };

    @Override
    public void sendDeath() {
        Snake.skin_id = 0;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics2D g) {
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(skin[Snake.skin_id], angle), x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
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
