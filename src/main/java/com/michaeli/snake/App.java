package com.michaeli.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class App extends JFrame {

    public static App APP;
    public static Snake SNAKE;

    public static final int HEIGHT = 400;
    public static final int WIDTH = 640;

    public static final int COMPONENT_SIZE = 20;
    public static final int SPEED = 1000; // => 1 'tick' => 1 Second = 1000 Milliseconds

    //Debug Options
    public static final boolean GRID = true;

    public static void main(String[] args) {
        System.out.println("[DEBUG] Game launched.");
        SNAKE = new Snake();
        APP = new App();
    }

    public App() {
        super("Snake");
        //Working w/ a fixed size atm, for better stability
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(SNAKE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //Draw Background
        //Draw Grid
        grid(g2d);
        //Draw Items
        //Draw Snake
        SNAKE.repaint();
    }

    public void grid(Graphics2D g) {
        for(int i = 0; i*COMPONENT_SIZE <= WIDTH; i++) {
            for(int j = 0; j*COMPONENT_SIZE <= HEIGHT; j++) {
                g.drawLine(i*COMPONENT_SIZE, 0, i*COMPONENT_SIZE, HEIGHT);
                g.drawLine(0, j*COMPONENT_SIZE, WIDTH, j*COMPONENT_SIZE);
            }
        }
    }
}
