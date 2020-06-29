package com.michaeli.snake;

import javax.swing.JFrame;

public class App extends JFrame {

    //Initialization

    //Constants
    public static App app;
    public static Snake snake;
    public static ConsumableFactory factory;
    public static KeyAdapter adapter;

    public static final int HEIGHT = 400;
    public static final int WIDTH = 640;

    public static final int COMPONENT_SIZE = 20;
    public static final int SPEED = 1000; // => 1 'tick' => 1 Second = 1000 Milliseconds

    //Debug Options
    public static final boolean GRID = true;
    
    //Main Method (Starts the program)
    public static void main(String[] args) {
        System.out.println("[DEBUG] Game launched!");
        app = new App();
        app.setup();
        app.addComponents();
        app.launch();
    }

    public static void debug() {
        System.out.println(snake.toString());
    }

    //Setup Functions
    public void setup() {
        setTitle("Snake");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //Components (Adding Window Components to the Window)
    public void addComponents() {
        snake = new Snake();
        adapter = new KeyAdapter();

        add(snake);
        addKeyListener(adapter);
    }

    //Launch (Snake Creation)
    public void launch() {
        snake.spawnSnake();
        factory.start();
    }
}