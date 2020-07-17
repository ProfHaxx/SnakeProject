package com.michaeli.snake;

import com.michaeli.launcher.Launcher;
import com.michaeli.snake.consumable.ConsumableFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAdapter implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code) {
            case KeyEvent.VK_B:
                App.debug();
                break;
            case KeyEvent.VK_A:
                App.field.snakes.get(0).setOrientation(2);
                break;
            case KeyEvent.VK_D:
                App.field.snakes.get(0).setOrientation(0);
                break;
            case KeyEvent.VK_W:
                App.field.snakes.get(0).setOrientation(1);
                break;
            case KeyEvent.VK_S:
                App.field.snakes.get(0).setOrientation(3);
                break;
            case KeyEvent.VK_LEFT:
                if(App.field.snakes.size() == 2) App.field.snakes.get(1).setOrientation(2);
                break;
            case KeyEvent.VK_RIGHT:
                if(App.field.snakes.size() == 2) App.field.snakes.get(1).setOrientation(0);
                break;
            case KeyEvent.VK_UP:
                if(App.field.snakes.size() == 2) App.field.snakes.get(1).setOrientation(1);
                break;
            case KeyEvent.VK_DOWN:
                if(App.field.snakes.size() == 2) App.field.snakes.get(1).setOrientation(3);
                break;
            case KeyEvent.VK_G:
                App.field.snakes.get(0).grow();
                break;
            case KeyEvent.VK_F2:
                Launcher.game.screenshot();
                break;
            case KeyEvent.VK_P:
                ConsumableFactory.spawn();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
