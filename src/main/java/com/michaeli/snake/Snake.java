package com.michaeli.snake;

import com.michaeli.snake.consumable.Consumable;
import com.michaeli.snake.consumable.ConsumableFactory;
import com.michaeli.snake.consumable.Effect;
import com.michaeli.snake.snake.SnakeHead;
import com.michaeli.snake.util.Utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

import static com.michaeli.snake.Obstacle.obstacles;

public class Snake {

    /*
    ⦿ Smooth Textures
    ⦿ Snake 'Terrain' Generation Fix
    ⦾ Menu Design
    ⦾ (Hotseat)-Multiplayer Option
    * */

    //Diverse Effect Handler
    public Effect[] effects = new Effect[]{new Effect(0, 0) {
        @Override
        public void tick() {
            System.out.println("[DEBUG/Effect] Ghost active!");
        }
    }, new Effect(1, 0) {
        @Override
        public void tick() {
            System.out.println("[DEBUG/Effect] Shadow active!");
        }
    }, new Effect(2, 0) {
        @Override
        public void tick() {
            System.out.println("[DEBUG/Effect] Speed Boost active!");
            App.SPEED *= 1.001;
        }
    }, new Effect(3, 0) {
        @Override
        public void tick() {
            System.out.println("[DEBUG/Effect] Speed Decrease active!");
            App.SPEED *= 0.999;
        }
    }};

    public static final int[] EFFECT_DURATION = new int[]{15, 30, 10, 10};

    //Effect Table
    //0: Ghost

    public SnakeHead head;
    public int orientation = 0; //Unit Circle: 90 deg. * orientation is snake's angle.
    public boolean dead = false;

    public Snake() {
        head = new SnakeHead();
    }

    public Snake(int id) {
        if(id == 1) {
            head = new SnakeHead(0, 0, 0);
        } else if(id == 2) {
            head = new SnakeHead(App.WIDTH/App.COMPONENT_SIZE, App.HEIGHT/App.COMPONENT_SIZE, 2);
        }
    }

    public void setOrientation(int orientation) {
        if(Math.abs(this.orientation - orientation) != 2) {
            this.orientation = orientation;
        }
    }

    //Wrap-Around Movement?
    public void move() {
        //Condition if out of map mit warp around
        System.out.println("[DEBUG] (" + head.getX() + "|" + head.getY() + ")");

        //Direction Switch
        if (orientation == 1) {
            head.move(0, -1);
        } else if (orientation == 2) {
            head.move(-1, 0);
        } else if (orientation == 3) {
            head.move(0, 1);
        } else {
            head.move(1, 0);
        }

        head.pushOrientation(orientation);
    }

    public void checkFood() {
        int foodIndex = -1;
        //List to avoid ConcurrentModificationException
        ArrayList<Consumable> temporaryList = new ArrayList<>(ConsumableFactory.consumables);
        for (Consumable consumable : temporaryList) {
            if (head.getX() == consumable.getX() && head.getY() == consumable.getY()) {
                foodIndex = ConsumableFactory.consumables.indexOf(consumable);
            }
        }
        if (foodIndex != -1) {
            Consumable food = ConsumableFactory.consumables.get(foodIndex);
            if (food.getId() == 0) {
                grow();
            } else if (food.getId() >= 1) {
                effects[food.getId()-1].start(EFFECT_DURATION[food.getId()-1]);
            }
            ConsumableFactory.eat(foodIndex);
        }
    }

    public void wrapAround() {
        if (head.getX() < 0) {
            head.setX(App.WIDTH / App.COMPONENT_SIZE);
        }
        if (head.getX() > App.WIDTH / App.COMPONENT_SIZE) {
            head.setX(0);
        }
        if (head.getY() < 0) {
            head.setY(App.HEIGHT / App.COMPONENT_SIZE);
        }
        if (head.getY() > App.HEIGHT / App.COMPONENT_SIZE) {
            head.setY(0);
        }
    }

    public void die() {
        dead = true;
        head.sendDeath();
        System.out.println("GAME OVER"); //evtl neues Fenster aufpoppen lassen?
        //Score?
    }

    public void checkDead() {
        if (!dead && !effects[0].active()) {
            //condition if Snake hits Obstacle
            for (Obstacle obstacle : obstacles) {
                if (head.getX() == obstacle.getX() && head.getY() == obstacle.getY()) {
                    die();
                }
            }

            if (head.isDead()) die();
        }
    }

    public void grow() {
        head.grow();
    }

    public boolean copying = false;
    public void nearSight(Graphics2D g) {
        BufferedImage screen = new BufferedImage(App.WIDTH, App.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        copying = true;
        App.field.paint(screen.getGraphics());
        copying = false;

        int[] pixel = {0, 0, 0, 0};
        float[] hsbvals = {0, 0, 0};

        for(int i = 0; i < screen.getHeight(); i++) {
            for(int j = 0; j < screen.getWidth(); j++) {
                screen.getRaster().getPixel(j, i, pixel);
                Color.RGBtoHSB(pixel[0], pixel[1], pixel[2], hsbvals);
                float brightness = (float) (1.0/(Math.pow((distToHead(j, i)+1)/10.0, 1)));
                Color adjusted = new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2] * brightness));
                screen.getRaster().setPixel(j, i, new int[]{adjusted.getRed(), adjusted.getGreen(), adjusted.getBlue(), pixel[3]});
            }
        }
        g.drawImage(screen, 0, 0, null);
    }

    public double distToHead(int px, int py) {
        return Math.sqrt(Math.pow((head.getX()*App.COMPONENT_SIZE+10)-px,2)+Math.pow((head.getY()*App.COMPONENT_SIZE+10)-py,2));
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head.toString() +
                ", orientation=" + orientation +
                ", dead=" + dead +
                '}';
    }
}
