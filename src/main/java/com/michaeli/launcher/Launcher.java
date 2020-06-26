package com.michaeli.launcher;

import com.michaeli.snake.App;
import com.michaeli.snake.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {

    public static Launcher launcher;
    public static App game;

    public static Snake snake;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;


    //main method (Startet alles -> alles was ausgeführt werden soll muss hier rein)
    public static void main(String[]args){
        launcher = new Launcher();
        launcher.setUpLauncher();
    }

    public void setUpLauncher(){
        setTitle("Snake Game Launcher");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        JButton start = new JButton("Start Game");
        start.setBounds(100, 100, 80, 50);
        launcher.add(start);
        start.addActionListener((ActionEvent e) -> {
            game = new App();
            game.setup();
            game.addComponents();
            game.launch();
        });
    }

    public void addComponents(){

    }
}
