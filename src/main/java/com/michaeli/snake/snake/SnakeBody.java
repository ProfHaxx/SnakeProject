package com.michaeli.snake.snake;

import com.michaeli.snake.App;
import com.michaeli.snake.Snake;
import com.michaeli.snake.Utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SnakeBody extends SnakeComponent {

    BufferedImage[] skin = new BufferedImage[]{
            Utility.getSnakeImage("snake/snake_dead.png")[1],
            Utility.getSnakeImage("snake/snake_green.png")[1],
            Utility.getSnakeImage("snake/snake_red.png")[1],
            Utility.getSnakeImage("snake/snake_blue.png")[1]
    };

    @Override
    public void paint(Graphics2D g) {
        int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
        g.drawImage(Utility.rotate(skin[Snake.skin_id], angle), x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
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
