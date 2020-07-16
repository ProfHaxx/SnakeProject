package com.michaeli.launcher;

import javax.swing.*;

public class Settings extends JFrame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    public void setUp(){
        setTitle("Settings");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void addComponents() {

    }

}
