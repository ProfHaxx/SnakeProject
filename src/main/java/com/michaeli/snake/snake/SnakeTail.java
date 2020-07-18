package com.michaeli.snake.snake;

import com.michaeli.snake.App;
import com.michaeli.snake.GameField;
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

    BufferedImage[] curve_skin = new BufferedImage[]{
            Utility.getSnakeImage("snake/snake_dead.png")[4],
            Utility.getSnakeImage("snake/snake_green.png")[4],
            Utility.getSnakeImage("snake/snake_red.png")[4],
            Utility.getSnakeImage("snake/snake_blue.png")[4]
    };

    @Override
    public void sendDeath() {
        GameField.skin_id = 0;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics2D g) {
        if(curved[0] == curved[1]) {
            int angle = (orientation%2==0) ? (orientation+1)*90 : (orientation-1)*90;
            g.drawImage(Utility.rotate(skin[GameField.skin_id], angle), x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        } else {
            int factor = 0;
            if(curved[0] == 0) {
                factor = (curved[1] == 1) ? 2 : 1;
            } else if(curved[0] == 1) {
                if(curved[1] == 2) factor = 1;
            } else if(curved[0] == 2) {
                if(curved[1] == 1) factor = 3;
            } else {
                factor = (curved[1] == 0) ? 3 : 2;
            }
            g.drawImage(Utility.rotate(curve_skin[GameField.skin_id], factor*90), x* App.COMPONENT_SIZE, y*App.COMPONENT_SIZE, null);
        }
    }

    @Override
    public boolean isDead(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public void pushOrientation(int orientation) {
        this.rotSkin(this.orientation, orientation);
        this.orientation = orientation;
    }

    @Override
    public int size() {
        return 1;
    }
}
