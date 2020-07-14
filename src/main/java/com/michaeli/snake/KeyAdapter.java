package com.michaeli.snake;

import com.michaeli.launcher.Launcher;

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
                App.snake.setOrientation(2);
                break;
            case KeyEvent.VK_D:
                App.snake.setOrientation(0);
                break;
            case KeyEvent.VK_W:
                App.snake.setOrientation(1);
                break;
            case KeyEvent.VK_S:
                App.snake.setOrientation(3);
                break;
            case KeyEvent.VK_G:
                App.snake.grow();
                break;
            case KeyEvent.VK_F2:
                Launcher.game.screenshot();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
