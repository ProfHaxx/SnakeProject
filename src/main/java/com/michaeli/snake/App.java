package com.michaeli.snake;

import com.michaeli.snake.consumable.ConsumableFactory;

import javax.swing.*;

public class App extends JFrame {

    //Initialization

    //Constants
    public static App app;
    public static GameField field;
    public static KeyAdapter adapter;

    public static final int HEIGHT = 400;
    public static final int WIDTH = 640; // für Snake ist eine Längeneinheit 20

    public static final int COMPONENT_SIZE = 20;
    public static int SPEED = 500; // 1000 => 1 'tick' => 1 Second = 1000 Milliseconds

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
        field.snakes.forEach(System.out::println);
    }

    //Setup Functions
    public void setup() {
        setTitle("Snake");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    //Components (Adding Window Components to the Window)
    public void addComponents() {
        field = new GameField(1, true, 3);
        adapter = new KeyAdapter();

        add(field);
        addKeyListener(adapter);
    }

    public void screenshot() {
        //https://stackoverflow.com/questions/8201705/java-awt-graphics-change-color-after-drawing
    }

    //Launch (Snake Creation)
    public void launch() {
        field.launch();
        Ground.generateGround();
        Obstacle.spawnObstacles();
        ConsumableFactory.start();
    }
}