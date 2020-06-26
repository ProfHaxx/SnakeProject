package com.michaeli.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class App extends JFrame {

    //initializing (Hier wird alles Initialisiert)

    //Constants
    public static App app;
    public static Snake snake;
    public static KeyAdapter adapter;

    public static final int HEIGHT = 400;
    public static final int WIDTH = 640;

    public static final int COMPONENT_SIZE = 20;
    public static final int SPEED = 1000; // => 1 'tick' => 1 Second = 1000 Milliseconds

    //Debug Options (zur Fehlerbehebung)
    public static final boolean GRID = true;
    
    //main methode (Startet alles -> alles was ausgeführt werden soll muss hier rein)
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

    //Setup Functions (Erstellung des JFrames)
    public void setup() {
        setTitle("Snake");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //Components (Hinzufügen der Fensterkomponenten zum Fenster)
    public void addComponents() {
        snake = new Snake();
        adapter = new KeyAdapter();

        add(snake);
        addKeyListener(adapter);
    }

    //Launch (Erstellung der Schlange)
    public void launch() {
        snake.spawnSnake();
    }
}
//NahCuy war hier