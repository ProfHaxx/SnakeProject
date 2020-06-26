package com.michaeli.launcher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {

    public static Launcher launcher;

    public static final int width = 640;
    public static final int hight = 400;


    //main method (Startet alles -> alles was ausgeführt werden soll muss hier rein)
    public static void main(String[]args){
        launcher = new Launcher();
        launcher.setUp();
        launcher.addComponents();
    }

    public void setUp(){
        setTitle("Snake Game Launcher");
        setSize(width, hight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        JButton start = new JButton("Start Game");
        start.setBounds(100, 100, 80, 50);
        launcher.add(start);
        start.addActionListener((ActionEvent e) -> {
            System.out.println("Would start the game if starting would be added");
        });
    }

    public void addComponents(){

    }
}
